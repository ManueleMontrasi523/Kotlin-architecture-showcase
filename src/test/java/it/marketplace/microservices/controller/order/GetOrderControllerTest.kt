package it.marketplace.microservices.controller.order

import it.marketplace.microservices.common.dto.OrderDto
import it.marketplace.microservices.common.dto.toResource
import it.marketplace.microservices.common.resource.OrderResource
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.OrderService
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

class GetOrderControllerTest : BaseTest() {

    @Mock
    private val service: OrderService = mock()

    @InjectMocks
    private val controller: GetOrderController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldReturnOrder_WhenFindByCode_ThenArrangeActAssert() {
        // Arrange
        val code = "ORD123"
        val dto = mockOrderDto()
        val resource: OrderResource? = dto.toResource()
        Mockito.`when`(service!!.findByCode(code)).thenReturn(dto)

        // Act
        val response: ResponseEntity<OrderResource> = controller!!.find(code)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals(resource, response.getBody())
        Mockito.verify(service).findByCode(code)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldReturnAllOrders_WhenFindAll_ThenArrangeActAssert() {
        // Arrange
        val dtos = listOf<OrderDto?>(mockOrderDto())
        Mockito.`when`(service.findAll()).thenReturn(dtos)

        // Act
        val response: ResponseEntity<List<OrderResource?>?> = controller.findAll()

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals(1, response.getBody()!!.size)
        Mockito.verify(service).findAll()
    }
}