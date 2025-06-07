package it.marketplace.microservices.rabbitmq

import it.marketplace.microservices.config.rabbitmq.RabbitMqConfig
import it.marketplace.microservices.service.TransactionService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

/**
 * RabbitMQ listener component for processing order and payment messages in the marketplace system.
 * Handles new order and pending payment events from the message queues.
 */
@Component
class MyRabbitListener(
    private val service: TransactionService
) {

    private val log = LoggerFactory.getLogger(MyRabbitListener::class.java)

    /**
     * Handles messages for new orders from the queue.
     * @param orderCode the order code received from the queue
     */
    @RabbitListener(queues = [RabbitMqConfig.NOTIFY_NEW_ORDER_QUEUE])
    fun listenerNewOrder(orderCode: String) {
        Thread.sleep(5000)
        log.info("Received message new order: {}", orderCode)
        service.startProcessing(orderCode)
    }

    /**
     * Handles messages for pending payments from the queue.
     * @param message the message map received from the queue
     */
    @RabbitListener(queues = [RabbitMqConfig.NOTIFY_PENDING_PAYMENT_QUEUE])
    fun listenerPendingPayment(message: Map<String, String>) {
        Thread.sleep(5000)
        log.info("Received message pending payment: {}", message)
        service.startPendingPayment(message)
    }
}

