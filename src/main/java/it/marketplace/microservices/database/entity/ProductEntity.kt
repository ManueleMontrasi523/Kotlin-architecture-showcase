package it.marketplace.microservices.database.entity

import it.marketplace.microservices.common.dto.ProductDto
import it.marketplace.microservices.common.enums.CategoryEnum
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Entity representing a product in the marketplace system.
 * Contains product details such as code, name, description, price, supply, category, and relevant dates.
 */
@Entity
@Table(name = "PRODUCT")
open class ProductEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_sequence", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    val id: Long,

    @Column(name = "PRODUCT_CODE", unique = true, nullable = false)
    var productCode: String,

    @Column(name = "NAME", nullable = false)
    var name: String,

    @Column(name = "DESCRIPTION")
    var description: String? = null,

    @Column(name = "PRICE", nullable = false)
    var price: Double,

    @Column(name = "SUPPLY", nullable = false)
    var supply: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY", nullable = false)
    var category: CategoryEnum,

    @DateTimeFormat
    @Column(name = "CREATION_DATE", nullable = false)
    var creationDate: LocalDateTime,

    @DateTimeFormat
    @Column(name = "TMS_UPDATE", nullable = false)
    var tmsUpdate: LocalDateTime
) : Serializable

fun ProductEntity.toDto() = ProductDto(
    id = this.id,
    productCode = this.productCode,
    name = this.name,
    description = this.description,
    price = this.price,
    supply = this.supply,
    category = this.category,
    creationDate = this.creationDate,
    tmsUpdate = this.tmsUpdate
)