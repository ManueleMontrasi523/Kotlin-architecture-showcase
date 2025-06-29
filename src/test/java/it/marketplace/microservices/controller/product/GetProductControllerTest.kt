package it.marketplace.microservices.controller.product

import it.marketplace.microservices.common.dto.toResource
import it.marketplace.microservices.common.resource.ProductResource
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.ProductService
import it.marketplace.microservices.utils.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals

class GetProductControllerTest : BaseTest() {

    @Mock
    private val service: ProductService = mock()

    @InjectMocks
    private val controller: GetProductController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldReturnProduct_WhenFindByCode_ThenArrangeActAssert() {
        // Arrange
        val code = "PROD123"
        val dto = mockProductDto()
        val expected: ProductResource? = dto.toResource()
        Mockito.`when`(service.findByCode(code)).thenReturn(dto)

        // Act
        val response: ResponseEntity<ProductResource> = controller.find(code)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals(expected, response.getBody())
        Mockito.verify(service).findByCode(code)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldReturnAllProducts_WhenFindAll_ThenArrangeActAssert() {
        // Arrange
        val dto = mockProductDto()
        val dtos = listOf(dto)
        val expected = listOf(dto.toResource())
        Mockito.`when`(service.findAll()).thenReturn(dtos)

        // Act
        val response: ResponseEntity<List<ProductResource>> = controller.findAll()

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals(expected, response.getBody())
        Mockito.verify(service).findAll()
    }
}