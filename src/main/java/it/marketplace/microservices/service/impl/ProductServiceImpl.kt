package it.marketplace.microservices.service.impl

import it.marketplace.microservices.common.dto.ProductDto
import it.marketplace.microservices.common.dto.ProductOrderDto
import it.marketplace.microservices.common.enums.ErrorCode
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.config.mapper.ProductMapper.toDto
import it.marketplace.microservices.config.mapper.ProductMapper.toEntity
import it.marketplace.microservices.database.entity.ProductEntity
import it.marketplace.microservices.database.entity.ProductOrderEntity
import it.marketplace.microservices.database.repository.ProductRepository
import it.marketplace.microservices.service.ProductService
import it.marketplace.microservices.utils.CopyProperties.copyNonNullProperties
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
open class ProductServiceImpl(
    private val repository: ProductRepository
) : ProductService {
    private val logger = LoggerFactory.getLogger(ProductServiceImpl::class.java)

    override fun save(dto: ProductDto) {
        try {
            val now = LocalDateTime.now()
            val entityOld = repository.findByProductCodeIgnoreCase(dto.productCode)
            if (entityOld != null)
                throw ServiceException(ErrorCode.DATA_ALREADY_PRESENT, "Product already registered")
            val entity = toEntity(dto)
            entity.creationDate = now
            entity.tmsUpdate = now
            repository.save(entity)
        } catch (e: ServiceException) {
            logger.error("ERROR in the class " + this::class.java.name + " with error ${e.fillInStackTrace()}")
            throw ServiceException(ErrorCode.GENERIC_ERROR, e.message)
        }
    }

    override fun saveAll(dto: List<ProductDto>) {
        try {
            val now = LocalDateTime.now()
            val productCodes = dto.map { it.productCode }.toList()
            val entityOld = repository.findAllByProductCodeIn(productCodes)
            if (entityOld.isNotEmpty())
                throw ServiceException(ErrorCode.DATA_ALREADY_PRESENT, "Products already registered")
            val entities = dto.map { toEntity(it) }
            entities.forEach { entity ->
                entity.creationDate = now
                entity.tmsUpdate = now
            }
            repository.saveAll(entities)
        } catch (e: ServiceException) {
            logger.error("ERROR in the class " + this::class.java.name + " with error ${e.fillInStackTrace()}")
            throw ServiceException(ErrorCode.GENERIC_ERROR, e.message)
        }
    }

    override fun saveAllDirectly(dto: List<ProductDto>) {
        val entities = dto.map { toEntity(it) }
        repository.saveAll(entities)
    }

    override fun findByCode(code: String): ProductDto {
        val entity = checkIfProductExist(code)
        return toDto(entity)
    }

    override fun findAll(): List<ProductDto> {
        val entities = repository.findAll()
        return entities.map { toDto(it) }
    }

    override fun update(dto: ProductDto) {
        val entity = checkIfProductExist(dto.productCode)
        copyNonNullProperties(dto, entity)
        entity.tmsUpdate = LocalDateTime.now()
        repository.save(entity)
    }

    override fun deleteByCode(code: String) {
        try {
            val entity = checkIfProductExist(code)
            repository.deleteById(entity.id!!)
        } catch (e: ServiceException) {
            throw ServiceException(ErrorCode.GENERIC_ERROR, e.message)
        }
    }

    @Transactional
    override fun updateProductStorageStatus(productOrderEntity: List<ProductOrderEntity>) {
        val productCodes = productOrderEntity.map { it.productCode }
        val entities = repository.findAllByProductCodeIn(productCodes)
        val now = LocalDateTime.now()
        val orderMap = productOrderEntity.associateBy { it.productCode }
        entities.forEach { entity ->
            val order = orderMap[entity.productCode]
            if (order != null) {
                entity.supply = entity.supply.subtract(order.quantity)
                entity.tmsUpdate = now
            }
        }
    }

    override fun checkRemainingSupplyProduct(productOrderEntity: List<ProductOrderDto>): List<String> {
        val productCodes = productOrderEntity.map { it.productCode }
        val entities = repository.findAllByProductCodeIn(productCodes)
        val orderMap = productOrderEntity.associateBy { it.productCode }
        return entities.filter { entity ->
            val order = orderMap[entity.productCode]
            order != null && order.quantity > entity.supply
        }.map { it.productCode }
    }

    private fun checkIfProductExist(code: String): ProductEntity {
        val entity = repository.findByProductCodeIgnoreCase(code)
        if (entity == null)
            throw ServiceException(ErrorCode.PRODUCT_NOT_FOUND, "Product with code: $code not found")
        return entity
    }
}

