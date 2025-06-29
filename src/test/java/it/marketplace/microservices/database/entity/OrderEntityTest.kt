package it.marketplace.microservices.database.entity

import it.marketplace.microservices.common.dto.toEntity
import it.marketplace.microservices.common.enums.StatusOrderEnum
import it.marketplace.microservices.utils.BaseTest
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OrderEntityTest : BaseTest() {

    @Test
    fun shouldCreateEntityWithAllArgsConstructor_ArrangeActAssert() {
        // Arrange/Act
        val user = mockUserDto().toEntity()
        val productOrder = mockProductOrderDto().toEntity()
        val now = LocalDateTime.now()
        val entity = OrderEntity(1L, "ORD123", user, listOf(productOrder), StatusOrderEnum.CREATED, "reason", now, now)
        // Assert
        assertEquals(1L, entity.id)
        assertEquals("ORD123", entity.orderCode)
        assertEquals(user, entity.user)
        assertEquals(1, entity.productOrder.size)
        assertEquals(StatusOrderEnum.CREATED, entity.status)
        assertEquals("reason", entity.rejectReason)
        assertEquals(now, entity.orderDate)
        assertEquals(now, entity.tmsUpdate)
    }

    @Test
    fun shouldSetAndGetFields_ArrangeActAssert() {
        // Arrange
        val entity = mockOrderDto().toEntity()
        val user = mockUserDto().toEntity()
        val productOrder = mockProductOrderDto().toEntity()
        val now = LocalDateTime.now()
        // Act
        entity.orderCode = "ORD456"
        entity.user = user
        entity.productOrder = listOf(productOrder)
        entity.status = StatusOrderEnum.REJECTED
        entity.rejectReason = "bad reason"
        entity.orderDate = now
        entity.tmsUpdate = now
        // Assert
        assertEquals("ORD456", entity.orderCode)
        assertEquals(user, entity.user)
        assertEquals(1, entity.productOrder.size)
        assertEquals(StatusOrderEnum.REJECTED, entity.status)
        assertEquals("bad reason", entity.rejectReason)
        assertEquals(now, entity.orderDate)
        assertEquals(now, entity.tmsUpdate)
    }

    @Test
    fun shouldTestEqualsAndHashCode_ArrangeActAssert() {
        // Arrange
        val e1 = mockOrderDto().toEntity()
        val e2 = mockOrderDto().toEntity()
        // Assert
        assertEquals(e1, e2)
        assertEquals(e1.hashCode(), e2.hashCode())
    }

    @Test
    fun shouldTestToString_ArrangeActAssert() {
        // Arrange
        val entity = mockOrderDto().toEntity()
        // Act
        val str = entity.toString()
        // Assert
        assertTrue(str.contains("ORD"))
    }
}