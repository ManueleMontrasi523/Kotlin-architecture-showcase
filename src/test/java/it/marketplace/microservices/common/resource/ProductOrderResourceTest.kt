package it.marketplace.microservices.common.resource

import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals

class ProductOrderResourceTest {
    @Test
    fun shouldCreateProductOrderResourceWithAllFields() {
        // Arrange
        val creationDate = LocalDateTime.now()
        val tmsUpdate = LocalDateTime.now()
        // Act
        val resource = ProductOrderResource(1L, "ORD123", "PROD1", BigDecimal.ONE, 10.0, 10.0, creationDate, tmsUpdate)
        // Assert
        assertEquals("ORD123", resource.orderCode)
        assertEquals("PROD1", resource.productCode)
        assertEquals(BigDecimal.ONE, resource.quantity)
        assertEquals(10.0, resource.unitPrice)
        assertEquals(10.0, resource.total)
        assertEquals(creationDate, resource.creationDate)
        assertEquals(tmsUpdate, resource.tmsUpdate)
    }

    @Test
    fun shouldSetAndGetTmsUpdate() {
        // Arrange
        val resource = ProductOrderResource(
            id = 1L,
            orderCode = "ORD123",
            productCode = "PROD1",
            quantity = BigDecimal.ONE,
            unitPrice = 10.0,
            total = 10.0,
            creationDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now()
        )
        val tmsUpdate = LocalDateTime.now()
        // Act
        resource.tmsUpdate = tmsUpdate
        // Assert
        assertEquals(tmsUpdate, resource.tmsUpdate)
    }
}

