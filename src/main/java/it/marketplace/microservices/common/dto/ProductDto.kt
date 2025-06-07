package it.marketplace.microservices.common.dto;

import it.marketplace.microservices.common.enums.CategoryEnum
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Data Transfer Object (DTO) representing a product in the marketplace system.
 * Contains product details such as code, name, description, price, supply, category, and relevant dates.
 */
data class ProductDto(

    var id: Long,
    var productCode: String,
    var name: String,
    var description: String,
    var PRICE: Double,
    var supply: BigDecimal,
    var category: CategoryEnum,

    var creationDate: LocalDateTime,
    var tmsUpdate: LocalDateTime

)
