package it.marketplace.microservices.controller.paymentOrder

import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.PaymentOrderService
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

class PutPaymentOrderControllerTest : BaseTest() {
    @Mock
    private val service: PaymentOrderService = mock()

    @InjectMocks
    private val controller: PutPaymentOrderController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldPayOrder_WhenValidOrderCodeAndInstallments_ThenArrangeActAssert() {
        // Arrange
        val orderCode = "ORD123"
        val isInstallments = true
        Mockito.doNothing().`when`(service).payOrder(orderCode, isInstallments)

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.pay(orderCode, isInstallments)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Order paid!", response.getBody()!!["message"])
        Mockito.verify(service).payOrder(orderCode, isInstallments)
    }
}