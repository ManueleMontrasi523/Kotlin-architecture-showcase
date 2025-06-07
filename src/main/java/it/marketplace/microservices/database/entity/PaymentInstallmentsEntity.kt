package it.marketplace.microservices.database.entity

import it.marketplace.microservices.common.enums.StatusOrderEnum
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Entity representing a payment installment in the marketplace system.
 * Contains details for simulating payments with multiple installments, including reference, order code, status, debit, and update timestamp.
 */
@Entity
@Table(name = "PAYMENT_INSTALLMENTS")
data class PaymentInstallmentsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_installments_seq")
    @SequenceGenerator(name = "payment_installments_seq", sequenceName = "payment_installments_sequence", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    val id: Long? = null,

    @Column(name = "REFERENCE", nullable = false)
    var reference: String,

    @Column(name = "ORDER_CODE", nullable = false)
    var orderCode: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    var status: StatusOrderEnum,

    @Column(name = "DEBIT", nullable = false)
    var debit: Double,

    @DateTimeFormat
    @Column(name = "TMS_UPDATE", nullable = false)
    var tmsUpdate: LocalDateTime
) : Serializable

