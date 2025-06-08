import it.marketplace.microservices.common.enums.StatusOrderEnum
import it.marketplace.microservices.common.resource.PaymentInstallmentsResource
import java.time.LocalDateTime

/**
 * Data Transfer Object representing payment installments.
 *
 * @property id Unique identifier of the installment.
 * @property reference Reference string for the installment.
 * @property orderCode Code of the related order.
 * @property status Status of the order (see [StatusOrderEnum]).
 * @property debit Amount to be debited for this installment.
 * @property tmsUpdate Timestamp of the last update.
 */
data class PaymentInstallmentsDto(
    val id: Long?,
    val reference: String,
    val orderCode: String,
    val status: StatusOrderEnum,
    var debit: Double,
    val tmsUpdate: LocalDateTime
)

fun PaymentInstallmentsDto.toResource() = PaymentInstallmentsResource(
    id = id,
    reference = reference,
    orderCode = orderCode,
    status = status,
    debit = debit,
    tmsUpdate = tmsUpdate
)
