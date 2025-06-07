package it.marketplace.microservices.common.dto

import it.marketplace.microservices.common.enums.StatusOrderEnum
import java.time.LocalDateTime

/**
 * Data Transfer Object (DTO) representing a payment order in the marketplace system.
 * Contains information about the payment order, including status, debit, paid amount, and relevant dates.
 */
data class PaymentOrderDto(

    var id: Long,

    var orderCode: String,
    var status: StatusOrderEnum,
    var debit: Double,
    var paid: Double,

    var orderDate: LocalDateTime,
    var tmsUpdate: LocalDateTime

)
