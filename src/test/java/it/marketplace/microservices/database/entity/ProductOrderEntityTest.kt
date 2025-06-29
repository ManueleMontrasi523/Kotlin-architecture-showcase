package it.marketplace.microservices.database.entity

import it.marketplace.microservices.common.dto.toEntity
import it.marketplace.microservices.utils.BaseTest
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals

class ProductOrderEntityTest : BaseTest() {
    @Test
    fun shouldCreateEntityWithAllArgsConstructor_ArrangeActAssert() {
        // Arrange/Act
        val now = LocalDateTime.now()
        val entity = ProductOrderEntity(1L, "ORD1", "PROD1", BigDecimal.ONE, 10.0, 10.0, now, now)
        // Assert
        assertEquals(1L, entity.id)
        assertEquals("ORD1", entity.orderCode)
        assertEquals("PROD1", entity.productCode)
        assertEquals(BigDecimal.ONE, entity.quantity)
        assertEquals(10.0, entity.unitPrice)
        assertEquals(10.0, entity.total)
        assertEquals(now, entity.creationDate)
        assertEquals(now, entity.tmsUpdate)
    }

    @Test
    fun shouldSetAndGetFields_ArrangeActAssert() {
        // Arrange
        val entity = mockProductOrderDto().toEntity()
        val now = LocalDateTime.now()
        // Act
        entity.orderCode = "ORD2"
        entity.productCode = "PROD2"
        entity.quantity = BigDecimal.TEN
        entity.unitPrice = 20.0
        entity.total = 200.0
        entity.creationDate = now
        entity.tmsUpdate = now
        // Assert
        assertEquals("ORD2", entity.orderCode)
        assertEquals("PROD2", entity.productCode)
        assertEquals(BigDecimal.TEN, entity.quantity)
        assertEquals(20.0, entity.unitPrice)
        assertEquals(200.0, entity.total)
        assertEquals(now, entity.creationDate)
        assertEquals(now, entity.tmsUpdate)
    }
}

