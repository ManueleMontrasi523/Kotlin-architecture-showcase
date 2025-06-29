package it.marketplace.microservices.controller.paymentInstallments

import it.marketplace.microservices.common.resource.PaymentInstallmentsResource
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.PaymentInstallmentsService
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
import toResource
import kotlin.test.assertEquals

class GetPaymentInstallmentsControllerTest : BaseTest() {
    @Mock
    private val service: PaymentInstallmentsService = mock()

    @InjectMocks
    private val controller: GetPaymentInstallmentsController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldReturnInstallments_WhenFindByOrderCode_ThenArrangeActAssert() {
        // Arrange
        val orderCode = "ORD123"
        val dtos = listOf(mockPaymentInstallmentsDto())
        val expected = listOf(mockPaymentInstallmentsDto().toResource())
        Mockito.`when`(service.findAllByCode(orderCode)).thenReturn(dtos)

        // Act
        val response: ResponseEntity<List<PaymentInstallmentsResource?>?> = controller.find(orderCode)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals(expected, response.getBody())
        Mockito.verify(service).findAllByCode(orderCode)
    }
}