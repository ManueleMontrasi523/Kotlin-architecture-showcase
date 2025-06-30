package it.marketplace.database.entity

import it.marketplace.common.enums.StatusOrderEnum
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class PaymentOrderEntityTest : BaseTest() {
    @Test
    fun shouldCreateEntityWithAllArgsConstructor_ArrangeActAssert() {
        // Arrange/Act
        val now = LocalDateTime.now()
        val entity = mockPaymentOrderEntity()
        // Assert
        assertEquals(1L, entity.id)
        assertEquals("ORD1", entity.orderCode)
        assertEquals(StatusOrderEnum.PAID, entity.status)
        assertEquals(100.0, entity.debit)
        assertEquals(now, entity.orderDate)
        assertEquals(now, entity.tmsUpdate)
    }

    @Test
    fun shouldSetAndGetFields_ArrangeActAssert() {
        // Arrange
        val now = LocalDateTime.now()
        val entity = mockPaymentOrderEntity()
        // Act
        entity.orderCode = "ORD2"
        entity.status = StatusOrderEnum.CREATED
        entity.debit = 200.0
        entity.orderDate = now
        entity.tmsUpdate = now
        // Assert
        assertEquals("ORD2", entity.orderCode)
        assertEquals(StatusOrderEnum.CREATED, entity.status)
        assertEquals(200.0, entity.debit)
        assertEquals(now, entity.orderDate)
        assertEquals(now, entity.tmsUpdate)
    }
}

