package it.marketplace.microservices.common.resource

import io.swagger.v3.oas.annotations.media.Schema
import it.marketplace.microservices.common.enums.StatusOrderEnum
import java.time.LocalDateTime

/**
 * Resource class representing a payment order in the marketplace system for API responses.
 * Contains payment order details, status, debit, paid amount, and relevant dates.
 */
data class PaymentOrderResource(

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var id: Long,

    var orderCode: String,
    var status: StatusOrderEnum,
    var debit: Double,
    var paid: Double,

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var orderDate: LocalDateTime,
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var tmsUpdate: LocalDateTime

)
