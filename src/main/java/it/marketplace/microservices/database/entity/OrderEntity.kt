package it.marketplace.microservices.database.entity

import it.marketplace.microservices.common.enums.StatusOrderEnum
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Entity representing an order in the marketplace system.
 * Maps to the ORDERS table and includes user, product orders, status, and timestamps.
 */
@Entity
@Table(name = "ORDERS")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    val id: Long,

    @Column(name = "ORDER_CODE", unique = true, nullable = false)
    var orderCode: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_USERS", referencedColumnName = "ID", nullable = false)
    var user: UserEntity,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "FK_ORDER", referencedColumnName = "ID", nullable = false)
    var productOrder: List<ProductOrderEntity> = emptyList(),

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    var status: StatusOrderEnum,

    @Column(name = "REJECT_REASON")
    var rejectReason: String? = null,

    @DateTimeFormat
    @Column(name = "ORDER_DATE", nullable = false)
    var orderDate: LocalDateTime,

    @DateTimeFormat
    @Column(name = "TMS_UPDATE", nullable = false)
    var tmsUpdate: LocalDateTime
) : Serializable