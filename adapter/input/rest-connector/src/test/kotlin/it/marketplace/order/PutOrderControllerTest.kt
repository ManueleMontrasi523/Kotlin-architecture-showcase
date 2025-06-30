package it.marketplace.controller.order

import it.marketplace.common.dto.toResource
import it.marketplace.config.exception.ServiceException
import it.marketplace.service.OrderService
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
import kotlin.test.assertEquals

class PutOrderControllerTest : BaseTest() {
    @Mock
    private val service: OrderService = mock()

    @InjectMocks
    private val controller: PutOrderController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldUpdateOrder_WhenValidResource_ThenArrangeActAssert() {
        // Arrange
        val dto = mockOrderDto()
        val resource = dto.toResource()
        Mockito.doNothing().`when`(service).update(dto)

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.update(resource)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Order Updated!", response.getBody()!!["message"])
        Mockito.verify(service).update(dto)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldCancelOrder_WhenValidOrderCode_ThenArrangeActAssert() {
        // Arrange
        val code = "ORD123"
        Mockito.doNothing().`when`(service).cancel(code)

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.cancel(code)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Order Cancelled!", response.getBody()!!["message"])
        Mockito.verify(service).cancel(code)
    }
}