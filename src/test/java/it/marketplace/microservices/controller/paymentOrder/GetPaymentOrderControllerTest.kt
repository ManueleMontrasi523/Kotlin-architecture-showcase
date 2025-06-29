package it.marketplace.microservices.controller.paymentOrder

import it.marketplace.microservices.common.dto.toResource
import it.marketplace.microservices.common.resource.PaymentOrderResource
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.PaymentOrderService
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

class GetPaymentOrderControllerTest : BaseTest() {
    @Mock
    private val service: PaymentOrderService = mock()

    @InjectMocks
    private val controller: GetPaymentOrderController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldReturnPaymentOrders_WhenFindByUserEmail_ThenArrangeActAssert() {
        // Arrange
        val email = "test@email.com"
        val dto = mockPaymentOrderDto()
        val dtos = listOf(dto)
        val expected = listOf(dto.toResource())
        Mockito.`when`(service.findOrderByEmail(email)).thenReturn(dtos)

        // Act
        val response: ResponseEntity<kotlin.collections.List<PaymentOrderResource?>?> = controller.find(email)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals(expected, response.getBody())
        Mockito.verify(service).findOrderByEmail(email)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldReturnAllPaymentOrders_WhenFindAll_ThenArrangeActAssert() {
        // Arrange
        val dto = mockPaymentOrderDto()
        val dtos = listOf(dto)
        val expected = listOf(dto.toResource())
        Mockito.`when`(service.findAll()).thenReturn(dtos)

        // Act
        val response: ResponseEntity<List<PaymentOrderResource?>?> = controller.findAll()

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals(expected, response.getBody())
        Mockito.verify(service).findAll()
    }
}