package it.marketplace.microservices.common.resource

import PaymentInstallmentsDto
import it.marketplace.microservices.common.enums.StatusOrderEnum
import java.time.LocalDateTime

/**
 * Resource class representing payment installments for an order in API responses.
 * Contains installment reference, order code, status, debit amount, and update timestamp.
 */
data class PaymentInstallmentsResource(

    var id: Long?,

    var reference: String,
    var orderCode: String,
    var status: StatusOrderEnum,
    var debit: Double,
    var tmsUpdate: LocalDateTime

)

fun PaymentInstallmentsResource.toDto() = PaymentInstallmentsDto(
    id = this.id,
    reference = this.reference,
    orderCode = this.orderCode,
    status = this.status,
    debit = this.debit,
    tmsUpdate = this.tmsUpdate
)