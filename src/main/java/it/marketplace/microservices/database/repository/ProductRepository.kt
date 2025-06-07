package it.marketplace.microservices.database.repository

import it.marketplace.microservices.database.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/**
 * Repository interface for managing ProductEntity persistence operations.
 * Provides methods to find products by code and by a list of codes.
 */
@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long> {
    /**
     * Finds a product by its code, ignoring case.
     * @param productCode the product code
     * @return the matching ProductEntity, or null if not found
     */
    fun findByProductCodeIgnoreCase(productCode: String): ProductEntity

    /**
     * Finds all products by a list of product codes.
     * @param productCode the list of product codes
     * @return a list of ProductEntity
     */
    fun findAllByProductCodeIn(productCode: List<String>): List<ProductEntity>

}
