package it.marketplace.microservices.common.resource

import it.marketplace.microservices.common.enums.CategoryEnum
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals

class ProductResourceTest {
    @Test
    fun shouldCreateProductResourceWithAllFields() {
        // Arrange
        val creationDate = LocalDateTime.now()
        // Act
        val resource = ProductResource(
            1L, "PROD1", "Product", "Description", 9.99, BigDecimal.TEN, CategoryEnum.TECHNOLOGIES, creationDate,
            LocalDateTime.now()
        )
        // Assert
        assertEquals(1L, resource.id)
        assertEquals("PROD1", resource.productCode)
        assertEquals("Product", resource.name)
        assertEquals("Description", resource.description)
        assertEquals(9.99, resource.price)
        assertEquals(BigDecimal.TEN, resource.supply)
        assertEquals(CategoryEnum.TECHNOLOGIES, resource.category)
        assertEquals(creationDate, resource.creationDate)
    }

    @Test
    fun shouldSetAndGetIdAndCreationDate() {
        // Arrange
        val resource = ProductResource(
            id = 1L,
            productCode = "PROD1",
            name = "Product",
            description = "Description",
            price = 9.99,
            supply = BigDecimal.TEN,
            category = CategoryEnum.TECHNOLOGIES,
            creationDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now()
        )
        val creationDate = LocalDateTime.now()
        // Act
        resource.id = 55L
        resource.creationDate = creationDate
        // Assert
        assertEquals(55L, resource.id)
        assertEquals(creationDate, resource.creationDate)
    }
}

