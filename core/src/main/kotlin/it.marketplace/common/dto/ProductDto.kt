package it.marketplace.common.dto

import it.marketplace.common.enums.CategoryEnum
import it.marketplace.common.resource.ProductResource
import it.marketplace.database.entity.ProductEntity
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Data Transfer Object (DTO) representing a product in the marketplace system.
 * Contains product details such as code, name, description, price, supply, category, and relevant dates.
 */
data class ProductDto(

    var id: Long?,
    var productCode: String,
    var name: String,
    var description: String,
    var price: Double,
    var supply: BigDecimal,
    var category: CategoryEnum,

    var creationDate: LocalDateTime?,
    var tmsUpdate: LocalDateTime?

)

fun ProductDto.toResource() = ProductResource(
    id = id,
    productCode = productCode,
    name = name,
    description = description,
    price = price,
    supply = supply,
    category = category,
    creationDate = creationDate,
    tmsUpdate = tmsUpdate
)

fun ProductDto.toEntity() = ProductEntity(
    id = id,
    productCode = productCode,
    name = name,
    description = description,
    price = price,
    supply = supply,
    category = category,
    creationDate = creationDate!!,
    tmsUpdate = tmsUpdate!!
)