package it.marketplace.microservices.config.rabbitmq

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuration class for RabbitMQ in the marketplace system.
 * Defines exchanges, queues, and bindings for order notifications.
 */
@Configuration
open class RabbitMqConfig {

    companion object {
        const val IT_EXCHANGE = "it.exchange"
        const val NOTIFY_NEW_ORDER_QUEUE = "notify-new-order-queue"
        const val NOTIFY_PENDING_PAYMENT_QUEUE = "notify-pending-payment-queue"
        const val NEW_ORDER_ROUTING_KEY = "notifyNewOrderRoutingKey"
        const val PENDING_PAYMENT_ROUTING_KEY = "notifyPendingPaymentRoutingKey"
    }

    /**
     * Defines the topic exchange for the application.
     * @return the TopicExchange bean
     */
    @Bean
    open fun itExchange(): TopicExchange = ExchangeBuilder.topicExchange(IT_EXCHANGE).durable(false).build()

    /**
     * Defines the queue for new order notifications.
     * @return the Queue bean
     */
    @Bean
    open fun notifyNewOrderQueue(): Queue = QueueBuilder.nonDurable(NOTIFY_NEW_ORDER_QUEUE).build()

    /**
     * Defines the queue for pending payment notifications.
     * @return the Queue bean
     */
    @Bean
    open fun notifyPendingPaymentQueue(): Queue = QueueBuilder.nonDurable(NOTIFY_PENDING_PAYMENT_QUEUE).build()

    /**
     * Binds the new order queue to the exchange with the new order routing key.
     * @return the Binding bean
     */
    @Bean
    open fun bindingNotifyNewOrderRoutingKey(): Binding =
        BindingBuilder.bind(notifyNewOrderQueue())
            .to(itExchange())
            .with(NEW_ORDER_ROUTING_KEY)

    /**
     * Binds the pending payment queue to the exchange with the pending payment routing key.
     * @return the Binding bean
     */
    @Bean
    open fun bindingNotifyPendingPaymentRoutingKey(): Binding =
        BindingBuilder.bind(notifyPendingPaymentQueue())
            .to(itExchange())
            .with(PENDING_PAYMENT_ROUTING_KEY)
}

