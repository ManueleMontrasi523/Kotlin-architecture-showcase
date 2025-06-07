package it.marketplace.microservices.service

import it.marketplace.microservices.common.dto.ProductDto
import it.marketplace.microservices.common.dto.ProductOrderDto
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.database.entity.ProductOrderEntity

/**
 * Service interface for managing products in the marketplace system.
 * Provides methods for product creation, update, deletion, inventory management, and supply checks.
 */
interface ProductService {
    /**
     * Saves a new product.
     * @param dto the product DTO to save
     * @throws ServiceException if the product already exists or another error occurs
     */
    @Throws(ServiceException::class)
    fun save(dto: ProductDto)

    /**
     * Saves a list of products.
     * @param dto the list of product DTOs to save
     */
    fun saveAll(dto: List<ProductDto>)

    /**
     * Saves a list of products directly without additional processing.
     * @param dto the list of product DTOs to save
     */
    fun saveAllDirectly(dto: List<ProductDto>)

    /**
     * Finds a product by its code.
     * @param code the product code
     * @return the matching ProductDto
     * @throws ServiceException if the product is not found
     */
    @Throws(ServiceException::class)
    fun findByCode(code: String): ProductDto

    /**
     * Finds all products in the system.
     * @return a list of ProductDto
     * @throws ServiceException if an error occurs
     */
    @Throws(ServiceException::class)
    fun findAll(): List<ProductDto>

    /**
     * Updates an existing product.
     * @param dto the product DTO with updated data
     * @throws ServiceException if the product is not found or another error occurs
     */
    @Throws(ServiceException::class)
    fun update(dto: ProductDto)

    /**
     * Deletes a product by its code.
     * @param code the product code to delete
     * @throws ServiceException if the product is not found or another error occurs
     */
    @Throws(ServiceException::class)
    fun deleteByCode(code: String)

    /**
     * Updates the storage status of products based on product orders.
     * @param productOrderEntity the list of product order entities
     */
    fun updateProductStorageStatus(productOrderEntity: List<ProductOrderEntity>)

    /**
     * Checks which products in the order exceed available supply.
     * @param productOrderEntity the list of product order DTOs
     * @return a list of product codes that exceed supply
     */
    fun checkRemainingSupplyProduct(productOrderEntity: List<ProductOrderDto>): List<String>
}

