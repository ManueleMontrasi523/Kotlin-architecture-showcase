package it.marketplace.microservices.common.resource

import it.marketplace.microservices.common.enums.StatusOrderEnum
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class PaymentInstallmentsResourceTest {
    @Test
    fun shouldCreatePaymentInstallmentsResourceWithAllFields() {
        // Arrange
        val tmsUpdate = LocalDateTime.now()
        // Act
        val resource = PaymentInstallmentsResource(1L, "REF123", "ORD123", StatusOrderEnum.PAID, 100.0, tmsUpdate)
        // Assert
        assertEquals(1L, resource.id)
        assertEquals("REF123", resource.reference)
        assertEquals("ORD123", resource.orderCode)
        assertEquals(StatusOrderEnum.PAID, resource.status)
        assertEquals(100.0, resource.debit)
        assertEquals(tmsUpdate, resource.tmsUpdate)
    }

    @Test
    fun shouldSetAndGetIdAndTmsUpdate() {
        // Arrange
        val resource = PaymentInstallmentsResource(
            id = null,
            reference = "REF123",
            orderCode = "ORD123",
            status = StatusOrderEnum.PAID,
            debit = 100.0,
            tmsUpdate = LocalDateTime.now()
        )
        val tmsUpdate = LocalDateTime.now()
        // Act
        resource.id = 42L
        resource.tmsUpdate = tmsUpdate
        // Assert
        assertEquals(42L, resource.id)
        assertEquals(tmsUpdate, resource.tmsUpdate)
    }
}

