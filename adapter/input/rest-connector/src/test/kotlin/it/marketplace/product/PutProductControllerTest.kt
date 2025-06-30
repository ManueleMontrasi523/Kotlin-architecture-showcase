package it.marketplace.controller.product

import it.marketplace.common.dto.toResource
import it.marketplace.common.resource.ProductResource
import it.marketplace.config.exception.ServiceException
import it.marketplace.config.validation.ProductValidator
import it.marketplace.service.ProductService
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import kotlin.test.assertEquals

class PutProductControllerTest : BaseTest() {

    @Mock
    private val service: ProductService = mock()

    @Mock
    private val validator: ProductValidator = mock()

    @Mock
    private val binder: WebDataBinder = mock()

    @InjectMocks
    private val controller: PutProductController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        controller.initBinder(binder)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldUpdateProduct_WhenValidResource_ThenArrangeActAssert() {
        // Arrange
        val resource = mockProductDto().toResource()
        Mockito.doNothing().`when`(service).update(mockProductDto())

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.update(resource)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Product updated!", response.getBody()!!["message"])
        Mockito.verify(service).update(mockProductDto())
    }
}