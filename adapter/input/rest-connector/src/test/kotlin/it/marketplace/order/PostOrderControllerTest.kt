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

class PostOrderControllerTest : BaseTest() {
    @Mock
    private val service: OrderService = mock()

    @InjectMocks
    private val controller: PostOrderController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldAddOrder_WhenValidResource_ThenArrangeActAssert() {
        // Arrange
        val dto = mockOrderDto()
        val resource = dto.toResource()
        Mockito.doNothing().`when`(service).save(dto)

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.save(resource)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Order added!", response.getBody()!!["message"])
        Mockito.verify(service).save(dto)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldAddAllOrders_WhenValidResources_ThenArrangeActAssert() {
        // Arrange
        val resource = mockOrderDto().toResource()
        val resources = listOf(resource)
        val dtos = listOf(mockOrderDto())
        Mockito.doNothing().`when`(service).saveAll(dtos)

        // Act
        val response: ResponseEntity<*> = controller.saveAll(resources)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Orders added!", (response.getBody() as MutableMap<*, *>)["message"])
        Mockito.verify(service).saveAll(dtos)
    }
}