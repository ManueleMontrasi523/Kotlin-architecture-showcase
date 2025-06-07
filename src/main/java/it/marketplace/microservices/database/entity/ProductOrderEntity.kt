package it.marketplace.microservices.database.entity

import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Entity representing a product order in the marketplace system.
 * Contains details about the ordered product, quantity, pricing, and relevant dates.
 */
@Entity
@Table(name = "PRODUCT_ORDER")
data class ProductOrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_order_seq")
    @SequenceGenerator(name = "product_order_seq", sequenceName = "product_order_sequence", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    val id: Long? = null,

    @Column(name = "ORDER_CODE", nullable = false)
    var orderCode: String,

    @Column(name = "PRODUCT_CODE", nullable = false)
    var productCode: String,

    @Column(name = "QUANTITY", nullable = false)
    var quantity: BigDecimal,

    @Column(name = "UNIT_PRICE", nullable = false)
    var unitPrice: Double,

    @Column(name = "TOTAL", nullable = false)
    var total: Double,

    @Column(name = "CREATION_DATE")
    var creationDate: LocalDateTime? = null,

    @Column(name = "TMS_UPDATE")
    var tmsUpdate: LocalDateTime? = null
) : Serializable

