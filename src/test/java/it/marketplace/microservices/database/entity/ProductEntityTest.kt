package it.marketplace.microservices.database.entity

import it.marketplace.microservices.common.dto.toEntity
import it.marketplace.microservices.common.enums.CategoryEnum
import it.marketplace.microservices.utils.BaseTest
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals

class ProductEntityTest : BaseTest() {
    @Test
    fun shouldCreateEntityWithAllArgsConstructor_ArrangeActAssert() {
        // Arrange/Act
        val now = LocalDateTime.now()
        val entity =
            ProductEntity(1L, "PROD1", "Name", "Desc", 10.5, BigDecimal.ONE, CategoryEnum.TECHNOLOGIES, now, now)
        // Assert
        assertEquals(1L, entity.id)
        assertEquals("PROD1", entity.productCode)
        assertEquals("Name", entity.name)
        assertEquals("Desc", entity.description)
        assertEquals(10.5, entity.price)
        assertEquals(BigDecimal.ONE, entity.supply)
        assertEquals(CategoryEnum.TECHNOLOGIES, entity.category)
        assertEquals(now, entity.creationDate)
        assertEquals(now, entity.tmsUpdate)
    }

    @Test
    fun shouldSetAndGetFields_ArrangeActAssert() {
        // Arrange
        val entity = mockProductDto().toEntity()
        val now = LocalDateTime.now()
        // Act
        entity.productCode = "PROD2"
        entity.name = "N"
        entity.description = "D"
        entity.price = 20.0
        entity.supply = BigDecimal.TEN
        entity.category = CategoryEnum.HOME
        entity.creationDate = now
        entity.tmsUpdate = now
        // Assert
        assertEquals("PROD2", entity.productCode)
        assertEquals("N", entity.name)
        assertEquals("D", entity.description)
        assertEquals(20.0, entity.price)
        assertEquals(BigDecimal.TEN, entity.supply)
        assertEquals(CategoryEnum.HOME, entity.category)
        assertEquals(now, entity.creationDate)
        assertEquals(now, entity.tmsUpdate)
    }
}

