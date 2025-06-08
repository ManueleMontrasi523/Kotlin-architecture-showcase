package it.marketplace.microservices.service.impl

import it.marketplace.microservices.common.dto.OrderDto
import it.marketplace.microservices.common.dto.toEntity
import it.marketplace.microservices.common.enums.ErrorCode
import it.marketplace.microservices.common.enums.StatusOrderEnum
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.database.entity.OrderEntity
import it.marketplace.microservices.database.entity.toDto
import it.marketplace.microservices.database.repository.OrderRepository
import it.marketplace.microservices.rabbitmq.RabbitMqProducer
import it.marketplace.microservices.service.OrderService
import it.marketplace.microservices.service.UserService
import it.marketplace.microservices.utils.OrderGenerator.generateOrderCode
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderServiceImpl @Autowired constructor(
    private val repository: OrderRepository,
    private val userService: UserService,
    private val producer: RabbitMqProducer
) : OrderService {
    private val logger = LoggerFactory.getLogger(OrderServiceImpl::class.java)

    override fun save(dto: OrderDto) {
        checkOrderOpenByUser(dto.user.email)
        val entity: OrderEntity = dto.toEntity()
        val managedUser = userService.findByEmailEntity(dto.user.email)
        entity.user = managedUser
        val orderCode = generateOrderCode()
        val now = LocalDateTime.now()
        entity.orderCode = orderCode
        entity.orderDate = now
        entity.tmsUpdate = now
        entity.productOrder.forEach { product ->
            product.orderCode = entity.orderCode
            product.creationDate = now
            product.tmsUpdate = now
        }
        repository.save(entity)
        producer.sendMessageNewOrder(orderCode)
    }

    override fun saveDirectly(dto: OrderDto) {
        repository.save(dto.toEntity())
    }

    override fun saveAll(dtos: List<OrderDto>) {
        try {
            val now = LocalDateTime.now()
            val emails = dtos.map { it.user.email }
            emails.forEach { checkOrderOpenByUser(it) }
            val entities = dtos.map { it.toEntity() }
            entities.forEach { entity ->
                entity.orderCode = generateOrderCode()
                entity.orderDate = now
                entity.tmsUpdate = now
                entity.productOrder.forEach { product ->
                    product.orderCode = entity.orderCode
                    product.creationDate = now
                    product.tmsUpdate = now
                }
            }
            repository.saveAll(entities)
        } catch (e: ServiceException) {
            logger.error("ERROR in the class {} with error {}", this::class.java.name, e.fillInStackTrace())
            throw ServiceException(ErrorCode.GENERIC_ERROR, e.message)
        }
    }

    override fun findByCode(code: String): OrderDto {
        return checkIfOrderExist(code).toDto()
    }

    override fun findByUserMail(email: String): List<OrderDto> {
        return repository.findOrderByUserMail(email).map { it.toDto() }
    }

    override fun findAll(): List<OrderDto> {
        return repository.findAll().map { it.toDto() }
    }

    override fun update(dto: OrderDto) {
        val entity = checkIfOrderExist(dto.orderCode)
        copyNonNullProperties(dto, entity)
        entity.tmsUpdate = LocalDateTime.now()
        repository.save(entity)
    }

    override fun deleteByCode(code: String) {
        try {
            val entity = checkIfOrderExist(code)
            repository.deleteById(entity.id)
        } catch (e: ServiceException) {
            throw ServiceException(ErrorCode.GENERIC_ERROR, e.message)
        }
    }

    override fun cancel(code: String) {
        val entity = checkIfOrderExist(code)
        entity.status = StatusOrderEnum.CANCELLED
        entity.tmsUpdate = LocalDateTime.now()
        repository.save(entity)
    }

    override fun payOrder(orderCode: String) {
        val entity = repository.findByOrderCodeIgnoreCase(orderCode)
        entity?.status = StatusOrderEnum.PAID
        entity?.tmsUpdate = LocalDateTime.now()
        repository.save(entity)
    }

    private fun checkIfOrderExist(code: String): OrderEntity {
        val entity = repository.findByOrderCodeIgnoreCase(code)
        if (entity == null)
            throw ServiceException(ErrorCode.ORDER_NOT_FOUND, "Order with code: $code not found")
        return entity
    }

    private fun checkOrderOpenByUser(email: String) {
        if (repository.findOrderByUserMailAndStatus(email, StatusOrderEnum.CREATED) != null)
            throw ServiceException(
                ErrorCode.ORDER_EXIST_FOR_USER_FOUND,
                "$email Can't create another list! Please wait."
            )
    }

    /**
     * Update only non-nullable dto properties on the entity.
     * Note: only rejectReason is nullable in OrderDto, so only this field is handled.
     * If there are other nullable fields in the future, add them here.
     */
    private fun copyNonNullProperties(dto: OrderDto, entity: OrderEntity) {
        entity.orderCode = dto.orderCode
        entity.user = dto.user.toEntity()
        entity.productOrder = dto.productOrder.map { it.toEntity() }
        entity.status = dto.status
        dto.rejectReason?.let { entity.rejectReason = it }
        entity.orderDate = dto.orderDate
        entity.tmsUpdate = dto.tmsUpdate
    }
}
