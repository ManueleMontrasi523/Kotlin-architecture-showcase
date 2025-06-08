package it.marketplace.microservices.common.dto

import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals

class ProductOrderDtoTest {
    @Test
    fun shouldCreateProductOrderDtoWithAllFields() {
        // Arrange
        val now = LocalDateTime.now()
        // Act
        val dto = ProductOrderDto(1L, "ORD123", "PROD1", BigDecimal.ONE, 10.0, 10.0, now, now)
        // Assert
        assertEquals("1", dto.id.toString())
        assertEquals("ORD123", dto.orderCode)
        assertEquals("PROD1", dto.productCode)
        assertEquals(BigDecimal.ONE, dto.quantity)
        assertEquals(10.0, dto.unitPrice)
        assertEquals(10.0, dto.total)
        assertEquals(now, dto.creationDate)
        assertEquals(now, dto.tmsUpdate)
    }

    @Test
    fun shouldSetAndGetTotal() {
        // Arrange
        val dto = ProductOrderDto(
            id = 1L,
            orderCode = "ORD123",
            productCode = "PROD1",
            quantity = BigDecimal.ONE,
            unitPrice = 10.0,
            total = 10.0,
            creationDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now()
        )
        // Act
        dto.total = 99.99
        // Assert
        assertEquals(99.99, dto.total)
    }
}

