package it.marketplace.service.impl

import it.marketplace.common.dto.ProductDto
import it.marketplace.common.dto.ProductOrderDto
import it.marketplace.common.dto.toEntity
import it.marketplace.common.enums.ErrorCode
import it.marketplace.config.exception.ServiceException
import it.marketplace.database.entity.ProductEntity
import it.marketplace.database.entity.ProductOrderEntity
import it.marketplace.database.entity.toDto
import it.marketplace.database.repository.ProductRepository
import it.marketplace.service.ProductService
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
            dto.creationDate = now
            dto.tmsUpdate = now
            repository.save(dto.toEntity())
        } catch (e: ServiceException) {
            logger.error("ERROR in the class " + this::class.java.name + " with error ${e.fillInStackTrace()}")
            throw ServiceException(ErrorCode.GENERIC_ERROR, e.message)
        }
    }

    override fun saveAll(dtos: List<ProductDto>) {
        try {
            val now = LocalDateTime.now()
            val productCodes = dtos.map { it.productCode }.toList()
            val entityOld = repository.findAllByProductCodeIn(productCodes)
            if (entityOld.isNotEmpty())
                throw ServiceException(ErrorCode.DATA_ALREADY_PRESENT, "Products already registered")
            dtos.forEach { dto ->
                dto.creationDate = now
                dto.tmsUpdate = now
            }
            repository.saveAll(dtos.map { it.toEntity() })
        } catch (e: ServiceException) {
            logger.error("ERROR in the class " + this::class.java.name + " with error ${e.fillInStackTrace()}")
            throw ServiceException(ErrorCode.GENERIC_ERROR, e.message)
        }
    }

    override fun saveAllDirectly(dto: List<ProductDto>) {
        repository.saveAll(dto.map { it.toEntity() })
    }

    override fun findByCode(code: String): ProductDto {
        return checkIfProductExist(code)!!.toDto()
    }

    override fun findAll(): List<ProductDto> {
        return repository.findAll().map { it.toDto() }
    }

    override fun update(dto: ProductDto) {
        val entity = checkIfProductExist(dto.productCode)!!
        copyNonNullProperties(dto, entity)
        entity.tmsUpdate = LocalDateTime.now()
        repository.save(entity)
    }

    override fun deleteByCode(code: String) {
        try {
            val entity = checkIfProductExist(code)!!
            repository.deleteById(entity.id)
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
        }.map { it.productCode.toString() }
    }

    private fun checkIfProductExist(code: String?): ProductEntity? {
        return repository.findByProductCodeIgnoreCase(code)
    }

    private fun copyNonNullProperties(dto: ProductDto, entity: ProductEntity) {
        dto.productCode.let { entity.productCode = it }
        dto.name.let { entity.name = it }
        dto.description.let { entity.description = it }
        dto.price.let { entity.price = it }
        dto.supply.let { entity.supply = it }
        dto.category.let { entity.category = it }
        dto.creationDate?.let { entity.creationDate = it }

        entity.tmsUpdate = LocalDateTime.now()
    }
}
