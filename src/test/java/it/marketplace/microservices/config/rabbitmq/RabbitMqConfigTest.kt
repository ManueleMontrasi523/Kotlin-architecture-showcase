package it.marketplace.microservices.config.rabbitmq

import org.junit.jupiter.api.Test
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import kotlin.test.assertEquals

class RabbitMqConfigTest {
    private val config = RabbitMqConfig()

    @Test
    fun shouldCreateExchange() {
        val exchange: TopicExchange = config.itExchange()
        assertEquals(RabbitMqConfig.IT_EXCHANGE, exchange.name)
    }

    @Test
    fun shouldCreateNotifyNewOrderQueue() {
        val queue: Queue = config.notifyNewOrderQueue()
        assertEquals(RabbitMqConfig.NOTIFY_NEW_ORDER_QUEUE, queue.name)
    }

    @Test
    fun shouldCreateNotifyPendingPaymentQueue() {
        val queue: Queue = config.notifyPendingPaymentQueue()
        assertEquals(RabbitMqConfig.NOTIFY_PENDING_PAYMENT_QUEUE, queue.name)
    }

    @Test
    fun shouldBindNotifyNewOrderQueue() {
        val binding: Binding = config.bindingNotifyNewOrderRoutingKey()
        assertEquals(RabbitMqConfig.NEW_ORDER_ROUTING_KEY, binding.routingKey)
    }

    @Test
    fun shouldBindNotifyPendingPaymentQueue() {
        val binding: Binding = config.bindingNotifyPendingPaymentRoutingKey()
        assertEquals(RabbitMqConfig.PENDING_PAYMENT_ROUTING_KEY, binding.routingKey)
    }
}

