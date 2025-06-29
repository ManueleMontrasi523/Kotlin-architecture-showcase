package it.marketplace.microservices.service

import it.marketplace.microservices.common.dto.ProductDto
import it.marketplace.microservices.common.dto.toEntity
import it.marketplace.microservices.database.repository.ProductRepository
import it.marketplace.microservices.service.impl.ProductServiceImpl
import it.marketplace.microservices.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertNotNull

class ProductServiceTest : BaseTest() {
    @Mock
    private val repository: ProductRepository = mock()

    @InjectMocks
    private val service: ProductServiceImpl = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun shouldFindByCode_WhenProductExists_ThenArrangeActAssert() {
        // Arrange
        val code = "PROD123"
        val entity = mockProductDto().toEntity()
        Mockito.`when`(repository.findByProductCodeIgnoreCase(code)).thenReturn(entity)
        // Act
        val result: ProductDto? = service.findByCode(code)
        // Assert
        assertNotNull(result)
        Mockito.verify(repository).findByProductCodeIgnoreCase(code)
    }
}

