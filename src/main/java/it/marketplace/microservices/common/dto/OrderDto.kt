package it.marketplace.microservices.common.dto;

import it.marketplace.microservices.common.enums.StatusOrderEnum
import java.time.LocalDateTime

/**
 * Data Transfer Object (DTO) representing an order in the marketplace system.
 * Contains information about the order, user, products, status, and relevant dates.
 */
data class OrderDto(

    var id: Long,
    var orderCode: String,
    var user: UserDto,
    var productOrder: List<ProductOrderDto>,
    var status: StatusOrderEnum,
    var rejectReason: String,
    var orderDate: LocalDateTime,
    var tmsUpdate: LocalDateTime

)
