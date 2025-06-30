package it.marketplace.rabbitmq

import it.marketplace.config.rabbitmq.RabbitMqConfig
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.springframework.amqp.rabbit.core.RabbitTemplate

class RabbitMqProducerTest : BaseTest() {

    @Mock
    private val rabbitTemplate: RabbitTemplate = mock()

    @InjectMocks
    private val producer: RabbitMqProducer = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun shouldSendMessageNewOrder_WhenCalled_ThenArrangeActAssert() {
        // Arrange
        val message = "ORD123"
        // Act
        producer.sendMessageNewOrder(message)
        // Assert
        Mockito.verify(rabbitTemplate).convertAndSend(
            RabbitMqConfig.IT_EXCHANGE,
            RabbitMqConfig.NEW_ORDER_ROUTING_KEY,
            message
        )
    }

    @Test
    fun shouldSendMessagePendingPayment_WhenCalled_ThenArrangeActAssert() {
        // Arrange
        val message = mapOf("orderCode" to "ORD123")
        // Act
        producer.sendMessagePendingPayment(message)
        // Assert
        Mockito.verify(rabbitTemplate).convertAndSend(
            RabbitMqConfig.IT_EXCHANGE,
            RabbitMqConfig.PENDING_PAYMENT_ROUTING_KEY,
            message
        )
    }
}

