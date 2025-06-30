package it.marketplace.controller.paymentInstallments

import it.marketplace.config.exception.ServiceException
import it.marketplace.service.PaymentInstallmentsService
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals

class PutPaymentInstallmentsControllerTest : BaseTest() {
    @Mock
    private val service: PaymentInstallmentsService = mock()

    @InjectMocks
    private val controller: PutPaymentInstallmentsController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldPayInstallment_WhenValidOrderCodeAndNumber_ThenArrangeActAssert() {
        // Arrange
        val orderCode = "ORD123"
        val number = 1
        Mockito.doNothing().`when`(service).payInstallments(orderCode, number)

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.pay(orderCode, number)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Rate paid!", response.getBody()!!["message"])
        Mockito.verify(service).payInstallments(orderCode, number)
    }
}