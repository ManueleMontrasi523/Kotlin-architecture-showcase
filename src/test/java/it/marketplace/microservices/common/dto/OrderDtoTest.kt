package it.marketplace.microservices.common.dto

import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusOrderEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals

class OrderDtoTest {

    @Test
    fun shouldCreateOrderDtoWithAllFields() {
        // Arrange
        val now = LocalDateTime.now()
        val user = UserDto(
            1L,
            "John",
            "Doe",
            "john.doe@email.com",
            "Main Street",
            "London",
            StatusUserEnum.ACTIVE,
            RoleEnum.CLIENT,
            now,
            now
        )
        val productOrder = ProductOrderDto(1L, "ORD123", "PROD1", BigDecimal.ONE, 10.0, 10.0, now, now)
        // Act
        val order = OrderDto(1L, "ORD123", user, listOf(productOrder), StatusOrderEnum.CREATED, null, now, now)
        // Assert
        assertEquals("1", order.id.toString())
        assertEquals("ORD123", order.orderCode)
        assertEquals(user, order.user)
        assertEquals(1, order.productOrder.size)
        assertEquals(StatusOrderEnum.CREATED, order.status)
        assertEquals(now, order.orderDate)
        assertEquals(now, order.tmsUpdate)
    }

    @Test
    fun shouldSetAndGetRejectReason() {
        // Arrange
        val product = ProductOrderDto(
            id = 1L,
            orderCode = "ORD123",
            productCode = "PROD1",
            unitPrice = 10.0,
            quantity = BigDecimal.ONE,
            tmsUpdate = LocalDateTime.now(),
            total = 10.0,
            creationDate = LocalDateTime.now()
        )

        val user = UserDto(
            id = 1L,
            name = "John",
            lastname = "Doe",
            email = "",
            residenceAddress = "Main Street",
            residenceCity = "London",
            status = StatusUserEnum.ACTIVE,
            role = RoleEnum.CLIENT,
            tmsSubscriptionDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now()
        )

        val order = OrderDto(
            id = 1L,
            orderCode = "ORD123",
            user = user,
            productOrder = listOf(product),
            status = StatusOrderEnum.CREATED,
            rejectReason = null,
            orderDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now()
        )
        // Act
        order.rejectReason = "Out of stock"
        // Assert
        assertEquals("Out of stock", order.rejectReason)
    }
}

