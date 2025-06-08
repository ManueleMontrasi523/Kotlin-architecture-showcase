package it.marketplace.microservices.database.entity

import it.marketplace.microservices.common.dto.PaymentOrderDto
import it.marketplace.microservices.common.enums.StatusOrderEnum
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Entity representing a payment order in the marketplace system.
 * Contains details for simulating completed payments, including order code, status, debit, and relevant dates.
 */
@Entity
@Table(name = "PAYMENT_ORDER")
open class PaymentOrderEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_order_seq")
    @SequenceGenerator(name = "payment_order_seq", sequenceName = "payment_order_sequence", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    val id: Long? = null,

    @Column(name = "ORDER_CODE", unique = true, nullable = false)
    var orderCode: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    var status: StatusOrderEnum,

    @Column(name = "DEBIT", nullable = false)
    var debit: Double,

    @DateTimeFormat
    @Column(name = "ORDER_DATE", nullable = false)
    var orderDate: LocalDateTime,

    @DateTimeFormat
    @Column(name = "TMS_UPDATE", nullable = false)
    var tmsUpdate: LocalDateTime
) : Serializable

fun PaymentOrderEntity.toDto() = PaymentOrderDto(
    id = this.id,
    orderCode = this.orderCode,
    status = this.status,
    debit = this.debit,
    orderDate = this.orderDate,
    tmsUpdate = this.tmsUpdate
)