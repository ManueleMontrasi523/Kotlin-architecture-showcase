package it.marketplace.job

import it.marketplace.rabbitmq.RabbitMqProducer
import it.marketplace.service.OrderService
import it.marketplace.service.ProductService
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class JobServiceTest : BaseTest() {

    @Mock
    private val producer: RabbitMqProducer = mock()

    @Mock
    private val orderService: OrderService = mock()

    @Mock
    private val productService: ProductService = mock()

    @InjectMocks
    private val jobService: JobService = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun shouldProcessOrderAndSendPendingPayment_WhenSupplyIsSufficient_ThenArrangeActAssert() {
        // Arrange
        val orderCode = "ORD123"
        val productOrderDto = mockProductOrderDto()
        productOrderDto.productCode = "PROD1"
        productOrderDto.quantity = BigDecimal.ONE
        productOrderDto.total = 10.0
        val orderDto = mockOrderDto()
        orderDto.productOrder = listOf(productOrderDto)
        Mockito.`when`(orderService.findByCode(orderCode)).thenReturn(orderDto)
        val productDto = mockProductDto()
        productDto.supply = BigDecimal.TEN
        Mockito.`when`(productService.findByCode("PROD1")).thenReturn(productDto)
        val message = mapOf("productCode" to "PROD1")
        // Act
        jobService.startProcessing(orderCode)
        // Assert
        Mockito.verify(productService).saveAllDirectly(listOf(mockProductDto()))
        Mockito.verify(orderService).saveDirectly(orderDto)
        Mockito.verify(producer).sendMessagePendingPayment(message)
    }

    @Test
    fun shouldRejectOrder_WhenSupplyIsInsufficient_ThenArrangeActAssert() {
        // Arrange
        val orderCode = "ORD124"
        val productOrderDto = mockProductOrderDto()
        productOrderDto.productCode = "PROD2"
        productOrderDto.quantity = BigDecimal.TEN
        productOrderDto.total = 100.0
        val orderDto = mockOrderDto()
        orderDto.productOrder = listOf(productOrderDto)
        Mockito.`when`(orderService.findByCode(orderCode)).thenReturn(orderDto)
        val productDto = mockProductDto()
        productDto.supply = BigDecimal.ONE
        Mockito.`when`(productService.findByCode("PROD2")).thenReturn(productDto)
        val message = mapOf("productCode" to "PROD1")
        // Act
        jobService.startProcessing(orderCode)
        // Assert
        Mockito.verify(productService).saveAllDirectly(listOf(mockProductDto()))
        Mockito.verify(orderService).saveDirectly(orderDto)
        Mockito.verify(producer, Mockito.never()).sendMessagePendingPayment(message)
    }
}

