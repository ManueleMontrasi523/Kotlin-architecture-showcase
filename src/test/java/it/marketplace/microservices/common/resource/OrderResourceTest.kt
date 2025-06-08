package it.marketplace.microservices.common.resource

import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusOrderEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals

class OrderResourceTest {

    @Test
    fun shouldCreateOrderResourceWithAllFields() {
        // Arrange
        val orderDate = LocalDateTime.now()
        val user = UserResource(
            1L, "John", "Doe", "john.doe@email.com", "Main Street", "London", StatusUserEnum.ACTIVE,
            RoleEnum.CLIENT, LocalDateTime.now(), LocalDateTime.now()
        )
        val productOrder = ProductOrderResource(1L, "ORD123", "PROD1", BigDecimal.ONE, 10.0, 10.0, orderDate, orderDate)
        // Act
        val resource = OrderResource(
            1L,
            "ORD123",
            user,
            listOf(productOrder),
            null,
            StatusOrderEnum.CREATED,
            orderDate,
            LocalDateTime.now()
        )
        // Assert
        assertEquals(1L, resource.id)
        assertEquals("ORD123", resource.orderCode)
        assertEquals(user, resource.user)
        assertEquals(1, resource.productOrder.size)
        assertEquals(StatusOrderEnum.CREATED, resource.status)
        assertEquals(orderDate, resource.orderDate)
    }

    @Test
    fun shouldSetAndGetIdAndOrderDate() {
        // Arrange
        val user = UserResource(
            1L, "John", "Doe", "john.doe@email.com", "Main Street", "London", StatusUserEnum.ACTIVE,
            RoleEnum.CLIENT, LocalDateTime.now(), LocalDateTime.now()
        )

        val resource = OrderResource(
            id = 0L,
            orderCode = "ORD123",
            user = user,
            productOrder = emptyList(),
            rejectReason = null,
            status = StatusOrderEnum.CREATED,
            orderDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now()
        )
        val orderDate = LocalDateTime.now()
        // Act
        resource.id = 77L
        resource.orderDate = orderDate
        // Assert
        assertEquals(77L, resource.id)
        assertEquals(orderDate, resource.orderDate)
    }
}

