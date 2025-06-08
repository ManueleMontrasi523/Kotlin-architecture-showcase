package it.marketplace.microservices.common.dto

import it.marketplace.microservices.common.enums.CategoryEnum
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals

class ProductDtoTest {
    @Test
    fun shouldCreateProductDtoWithAllFields() {
        // Arrange
        val now = LocalDateTime.now()
        // Act
        val dto =
            ProductDto(1L, "PROD1", "Product", "Description", 9.99, BigDecimal.TEN, CategoryEnum.TECHNOLOGIES, now, now)
        // Assert
        assertEquals("1", dto.id.toString())
        assertEquals("PROD1", dto.productCode)
        assertEquals("Product", dto.name)
        assertEquals(9.99, dto.price)
        assertEquals(BigDecimal.TEN, dto.supply)
        assertEquals(CategoryEnum.TECHNOLOGIES, dto.category)
        assertEquals(now, dto.creationDate)
        assertEquals(now, dto.tmsUpdate)
    }

    @Test
    fun shouldSetAndGetDescription() {
        // Arrange
        val dto = ProductDto(
            id = 1L,
            productCode = "PROD1",
            name = "Product",
            description = "Initial description",
            price = 9.99,
            supply = BigDecimal.TEN,
            category = CategoryEnum.TECHNOLOGIES,
            creationDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now()
        )
        // Act
        dto.description = "New description"
        // Assert
        assertEquals("New description", dto.description)
    }
}

