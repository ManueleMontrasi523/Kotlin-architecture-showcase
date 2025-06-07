package it.marketplace.microservices.job

import it.marketplace.microservices.common.dto.ProductDto
import it.marketplace.microservices.common.enums.StatusOrderEnum.FAILED
import it.marketplace.microservices.common.enums.StatusOrderEnum.REJECTED
import it.marketplace.microservices.rabbitmq.RabbitMqProducer
import it.marketplace.microservices.service.OrderService
import it.marketplace.microservices.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * Service class for background job processing related to orders in the marketplace system.
 * Handles order validation, product supply checks, updates, and notifies via RabbitMQ.
 */
@Service
open class JobService @Autowired constructor(
    private val producer: RabbitMqProducer,
    @Lazy private val orderService: OrderService,
    private val productService: ProductService
) {
    private val log = LoggerFactory.getLogger(JobService::class.java)

    /**
     * Starts asynchronous processing for a given order code.
     * Validates product supply, updates product and order status, and sends notifications.
     *
     * @param orderCode the code of the order to process
     */
    @Async
    open fun startProcessing(orderCode: String) {
        val orderDto = orderService.findByCode(orderCode)
        log.info("Start job for $orderCode, find entity: $orderDto")
        try {
            Thread.sleep(5000)
            val rejectReason = mutableMapOf<String, String>()
            var debit = 0.0
            val toUpdate = mutableListOf<ProductDto>()

            for (productOrderDto in orderDto.productOrder) {
                val productDto = productService.findByCode(productOrderDto.productCode)
                val supply = productDto.supply
                val order = productOrderDto.quantity
                if (supply < order) {
                    rejectReason[productOrderDto.productCode] = "Exceed limit of $supply requested: $order"
                } else {
                    productDto.supply = supply - order
                    toUpdate.add(productDto)
                }
                debit += productOrderDto.total
            }

            log.info("Check reason for reject order $orderCode - $rejectReason")

            if (rejectReason.isNotEmpty()) {
                orderDto.rejectReason = rejectReason.toString()
                orderDto.status = REJECTED
            }

            productService.saveAllDirectly(toUpdate)
            orderService.saveDirectly(orderDto)

            val message = mutableMapOf<String, String>()
            message["orderCode"] = orderCode
            message["debit"] = debit.toString()

            if (rejectReason.isEmpty()) producer.sendMessagePendingPayment(message)
        } catch (ex: Exception) {
            log.error("Error during the jobService for order $orderCode with error", ex)
            orderDto.status = FAILED
            orderService.saveDirectly(orderDto)
        }
    }
}

