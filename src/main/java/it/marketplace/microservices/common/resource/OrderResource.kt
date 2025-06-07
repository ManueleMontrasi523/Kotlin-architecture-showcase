package it.marketplace.microservices.common.resource;

import io.swagger.v3.oas.annotations.media.Schema
import it.marketplace.microservices.common.enums.StatusOrderEnum
import java.time.LocalDateTime
import java.util.List

/**
 * Resource class representing an order in the marketplace system for API responses.
 * Contains order details, user information, product list, status, and order date.
 */
data class OrderResource(

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var id: Long,
    var orderCode: String,
    var userResource: UserResource,
    var productResource: List<ProductOrderResource>,
    var status: StatusOrderEnum,
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var orderDate: LocalDateTime

)
