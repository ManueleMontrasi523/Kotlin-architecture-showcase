package it.marketplace.microservices.common.resource

import io.swagger.v3.oas.annotations.media.Schema
import it.marketplace.microservices.common.dto.ProductOrderDto
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Resource class representing a product order in the marketplace system for API responses.
 * Contains details about the ordered product, quantity, pricing, and relevant dates.
 */
data class ProductOrderResource(

    var id: Long,
    var orderCode: String,
    var productCode: String,
    var quantity: BigDecimal,
    var unitPrice: Double,
    var total: Double,

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var creationDate: LocalDateTime,
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var tmsUpdate: LocalDateTime

)

fun ProductOrderResource.toDto() = ProductOrderDto(
    id = id,
    orderCode = orderCode,
    productCode = productCode,
    quantity = quantity,
    unitPrice = unitPrice,
    total = total,
    creationDate = creationDate,
    tmsUpdate = tmsUpdate
)