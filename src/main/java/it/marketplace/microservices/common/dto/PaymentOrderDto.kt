package it.marketplace.microservices.common.dto

import it.marketplace.microservices.common.enums.StatusOrderEnum
import it.marketplace.microservices.common.resource.PaymentOrderResource
import java.time.LocalDateTime

/**
 * Data Transfer Object (DTO) representing a payment order in the marketplace system.
 * Contains information about the payment order, including status, debit, paid amount, and relevant dates.
 */
data class PaymentOrderDto(

    var id: Long?,

    var orderCode: String,
    var status: StatusOrderEnum,
    var debit: Double,

    var orderDate: LocalDateTime,
    var tmsUpdate: LocalDateTime

)

fun PaymentOrderDto.toResource() = PaymentOrderResource(
    id = id,
    orderCode = orderCode,
    status = status,
    debit = debit,
    orderDate = orderDate,
    tmsUpdate = tmsUpdate
)
