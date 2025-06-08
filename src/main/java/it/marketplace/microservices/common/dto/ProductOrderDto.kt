package it.marketplace.microservices.common.dto

import it.marketplace.microservices.common.resource.ProductOrderResource
import it.marketplace.microservices.database.entity.ProductOrderEntity
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Data Transfer Object (DTO) representing a product order in the marketplace system.
 * Contains details about the product ordered, quantity, pricing, and relevant dates.
 */
data class ProductOrderDto(
    var id: Long,
    var orderCode: String,
    var productCode: String,
    var quantity: BigDecimal,
    var unitPrice: Double,
    var total: Double,
    var creationDate: LocalDateTime,
    var tmsUpdate: LocalDateTime
)

fun ProductOrderDto.toResource() = ProductOrderResource(
    id = this.id,
    orderCode = this.orderCode,
    productCode = this.productCode,
    quantity = this.quantity,
    unitPrice = this.unitPrice,
    total = this.total,
    creationDate = this.creationDate,
    tmsUpdate = this.tmsUpdate
)

fun ProductOrderDto.toEntity() = ProductOrderEntity(
    id = this.id,
    orderCode = this.orderCode,
    productCode = this.productCode,
    quantity = this.quantity,
    unitPrice = this.unitPrice,
    total = this.total,
    creationDate = this.creationDate,
    tmsUpdate = this.tmsUpdate
)