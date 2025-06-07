import it.marketplace.microservices.common.enums.StatusOrderEnum
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
    val id: Long? = null,
    val reference: String? = null,
    val orderCode: String? = null,
    val status: StatusOrderEnum? = null,
    val debit: Double? = null,
    val tmsUpdate: LocalDateTime? = null
)

