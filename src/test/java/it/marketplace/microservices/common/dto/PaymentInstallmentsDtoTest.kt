package it.marketplace.microservices.common.dto

import PaymentInstallmentsDto
import it.marketplace.microservices.common.enums.StatusOrderEnum
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class PaymentInstallmentsDtoTest {
    @Test
    fun shouldCreatePaymentInstallmentsDtoWithAllFields() {
        // Arrange
        val now = LocalDateTime.now()
        // Act
        val dto = PaymentInstallmentsDto(1L, "REF123", "ORD123", StatusOrderEnum.PAID, 100.0, now)
        // Assert
        assertEquals("1", dto.id.toString())
        assertEquals("REF123", dto.reference)
        assertEquals("ORD123", dto.orderCode)
        assertEquals(StatusOrderEnum.PAID, dto.status)
        assertEquals(100.0, dto.debit)
        assertEquals(now, dto.tmsUpdate)
    }

    @Test
    fun shouldSetAndGetDebit() {
        // Arrange
        val dto = PaymentInstallmentsDto(
            id = 1L,
            reference = "REF123",
            orderCode = "ORD123",
            status = StatusOrderEnum.PAID,
            debit = 100.0,
            tmsUpdate = LocalDateTime.now()
        )
        // Act
        dto.debit = 250.0
        // Assert
        assertEquals(250.0, dto.debit)
    }
}

