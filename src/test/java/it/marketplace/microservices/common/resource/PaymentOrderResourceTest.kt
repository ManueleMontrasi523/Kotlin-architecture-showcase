package it.marketplace.microservices.common.resource

import it.marketplace.microservices.common.enums.StatusOrderEnum
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class PaymentOrderResourceTest {
    @Test
    fun shouldCreatePaymentOrderResourceWithAllFields() {
        // Arrange
        val orderDate = LocalDateTime.now()
        val tmsUpdate = LocalDateTime.now()
        // Act
        val resource = PaymentOrderResource(1L, "ORD123", StatusOrderEnum.PAID, 100.0, orderDate, tmsUpdate)
        // Assert
        assertEquals(1L, resource.id)
        assertEquals("ORD123", resource.orderCode)
        assertEquals(StatusOrderEnum.PAID, resource.status)
        assertEquals(100.0, resource.debit)
        assertEquals(orderDate, resource.orderDate)
        assertEquals(tmsUpdate, resource.tmsUpdate)
    }

    @Test
    fun shouldSetAndGetIdAndTmsUpdate() {
        // Arrange
        val resource = PaymentOrderResource(
            id = 1L,
            orderCode = "ORD123",
            status = StatusOrderEnum.PAID,
            debit = 100.0,
            orderDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now()
        )
        val tmsUpdate = LocalDateTime.now()
        // Act
        resource.id = 99L
        resource.tmsUpdate = tmsUpdate
        // Assert
        assertEquals(99L, resource.id)
        assertEquals(tmsUpdate, resource.tmsUpdate)
    }
}

