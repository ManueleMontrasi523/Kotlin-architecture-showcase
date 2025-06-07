package it.marketplace.microservices.common.resource

import it.marketplace.microservices.common.enums.StatusOrderEnum
import java.time.LocalDateTime

/**
 * Resource class representing payment installments for an order in API responses.
 * Contains installment reference, order code, status, debit amount, and update timestamp.
 */
data class PaymentInstallmentsResource(

    var id: Long,

    var reference: String,
    var orderCode: String,
    var status: StatusOrderEnum,
    var debit: Double,
    var tmsUpdate: LocalDateTime

)