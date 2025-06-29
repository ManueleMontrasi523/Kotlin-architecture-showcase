package it.marketplace.microservices.controller.product

import it.marketplace.microservices.common.dto.toResource
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.ProductService
import it.marketplace.microservices.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals

class PostProductControllerTest : BaseTest() {

    @Mock
    private val service: ProductService = mock()

    @InjectMocks
    private val controller: PostProductController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldAddProduct_WhenValidResource_ThenArrangeActAssert() {
        // Arrange
        val dto = mockProductDto()
        val resource = dto.toResource()
        Mockito.doNothing().`when`(service).save(dto)

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.save(resource)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Product added!", response.getBody()!!["message"])
        Mockito.verify(service).save(dto)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldAddAllProducts_WhenValidResources_ThenArrangeActAssert() {
        // Arrange
        val resource = mockProductDto().toResource()
        val resources = listOf(resource)
        val dtos = listOf(mockProductDto())
        Mockito.doNothing().`when`(service).saveAll(dtos)

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.saveAll(resources)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Products added!", response.getBody()!!["message"])
        Mockito.verify(service).saveAll(dtos)
    }
}