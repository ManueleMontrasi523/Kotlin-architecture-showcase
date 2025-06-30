package it.marketplace.database.repository

import it.marketplace.common.dto.toEntity
import it.marketplace.common.enums.CategoryEnum
import it.marketplace.database.entity.ProductEntity
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

@DataJpaTest
open class ProductRepositoryTest : BaseTest() {

    @Autowired
    private val repository: ProductRepository = mock()

    @Test
    fun shouldFindByProductCodeIgnoreCase_ArrangeActAssert() {
        // Arrange
        val entity = mockProductDto().toEntity()
        entity.productCode = ("PROD1")
        entity.name = ("TEST")
        entity.price = (0.59)
        entity.supply = (BigDecimal.valueOf(500))
        entity.category = (CategoryEnum.TECHNOLOGIES)
        entity.creationDate = (LocalDateTime.now())
        entity.tmsUpdate = (LocalDateTime.now())
        repository.save(entity)
        // Act
        val found: ProductEntity? = repository.findByProductCodeIgnoreCase("prod1")
        // Assert
        assertNotNull(found)
        assertEquals("PROD1", found.productCode)
    }

    @Test
    fun shouldFindAllByProductCodeIn_ArrangeActAssert() {
        // Arrange
        val entity = mockProductDto().toEntity()
        entity.productCode = ("PROD2")
        entity.name = ("TEST")
        entity.price = (0.59)
        entity.supply = (BigDecimal.valueOf(500))
        entity.category = (CategoryEnum.TECHNOLOGIES)
        entity.creationDate = (LocalDateTime.now())
        entity.tmsUpdate = (LocalDateTime.now())
        repository.save(entity)
        // Act
        val found: List<ProductEntity?> = repository.findAllByProductCodeIn(listOf("PROD2"))
        // Assert
        assertFalse(found.isEmpty())
        assertEquals("PROD2", found[0]?.productCode)
    }
}

