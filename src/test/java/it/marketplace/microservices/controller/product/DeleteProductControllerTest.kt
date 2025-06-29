package it.marketplace.microservices.controller.product

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

class DeleteProductControllerTest : BaseTest() {

    @Mock
    private val service: ProductService = mock()

    @InjectMocks
    private val controller: DeleteProductController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldDeleteProduct_WhenValidProductCode_ThenArrangeActAssert() {
        // Arrange
        val productCode = "PROD123"
        Mockito.doNothing().`when`(service).deleteByCode(productCode)

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.delete(productCode)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Product deleted!", response.getBody()!!["message"])
        Mockito.verify(service).deleteByCode(productCode)
    }
}