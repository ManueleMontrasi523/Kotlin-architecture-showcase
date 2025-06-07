package it.marketplace.microservices.common.resource

import io.swagger.v3.oas.annotations.media.Schema
import it.marketplace.microservices.common.enums.CategoryEnum
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Resource class representing a product in the marketplace system for API responses.
 * Contains product details such as code, name, description, price, supply, category, and creation date.
 */
data class ProductResource(

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var id: Long,

    var productCode: String,
    val name: String,
    var description: String,
    var price: Double,
    var supply: BigDecimal,
    var category: CategoryEnum,

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var creationDate: LocalDateTime

)