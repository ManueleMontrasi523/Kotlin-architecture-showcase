package it.marketplace.microservices.common.resource

import io.swagger.v3.oas.annotations.media.Schema
import it.marketplace.microservices.common.dto.OrderDto
import it.marketplace.microservices.common.enums.StatusOrderEnum
import java.time.LocalDateTime


/**
 * Resource class representing an order in the marketplace system for API responses.
 * Contains order details, user information, product list, status, and order date.
 */
data class OrderResource(

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var id: Long,
    var orderCode: String,
    var user: UserResource,
    var productOrder: List<ProductOrderResource>,
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var rejectReason: String? = null,
    var status: StatusOrderEnum,
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var orderDate: LocalDateTime,
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    val tmsUpdate: LocalDateTime

)

fun OrderResource.toDto() = OrderDto(
    id = this.id,
    orderCode = this.orderCode,
    user = this.user.toDto(),
    productOrder = this.productOrder.map { it.toDto() },
    rejectReason = this.rejectReason,
    status = this.status,
    orderDate = this.orderDate,
    tmsUpdate = this.tmsUpdate
)
