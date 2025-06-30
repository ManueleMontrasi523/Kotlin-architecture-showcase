package it.marketplace.controller.order

import it.marketplace.service.OrderService
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals

class DeleteOrderControllerTest : BaseTest() {

    @Mock
    private lateinit var service: OrderService

    @InjectMocks
    private lateinit var controller: DeleteOrderController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should delete order when valid order code then arrange act assert`() {
        // Arrange
        val orderCode = "ORD123"
        doNothing().`when`(service).deleteByCode(orderCode)

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.delete(orderCode)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Order deleted!", response.body?.getValue("message"))
        verify(service).deleteByCode(orderCode)
    }
}
