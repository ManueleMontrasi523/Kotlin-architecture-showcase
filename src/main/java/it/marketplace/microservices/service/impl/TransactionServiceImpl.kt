package it.marketplace.microservices.service.impl

import it.marketplace.microservices.common.enums.StatusOrderEnum
import it.marketplace.microservices.database.entity.OrderEntity
import it.marketplace.microservices.database.entity.PaymentOrderEntity
import it.marketplace.microservices.database.repository.OrderRepository
import it.marketplace.microservices.database.repository.PaymentOrderRepository
import it.marketplace.microservices.job.JobService
import it.marketplace.microservices.service.TransactionService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TransactionServiceImpl(
    private val orderRepository: OrderRepository,
    private val paymentOrderRepository: PaymentOrderRepository,
    private val job: JobService
) : TransactionService {

    private val logger = LoggerFactory.getLogger(TransactionServiceImpl::class.java)

    override fun startProcessing(orderCode: String) {
        logger.info("Arrived new order with code {} in status CREATED", orderCode)
        val entity: OrderEntity? = orderRepository.findByOrderCodeIgnoreCase(orderCode)
        if (entity != null) {
            entity.status = StatusOrderEnum.PROCESSING
            entity.tmsUpdate = LocalDateTime.now()
            orderRepository.save(entity)
            logger.info("Update status in PROCESSING for code {}", orderCode)
            job.startProcessing(orderCode)
        }
    }

    override fun startPendingPayment(message: Map<String, String>) {
        logger.info("Start process pending payment with code {}", message)
        val entity = orderRepository.findByOrderCodeIgnoreCase(message["orderCode"]!!)
        if (entity != null && entity.status == StatusOrderEnum.PROCESSING) {
            val now = LocalDateTime.now()
            entity.status = StatusOrderEnum.PENDING_PAYMENT
            entity.tmsUpdate = now

            val paymentOrderEntity = PaymentOrderEntity(
                orderCode = message["orderCode"]!!,
                status = StatusOrderEnum.PENDING_PAYMENT,
                debit = message["debit"]?.toDouble() ?: 0.0,
                orderDate = now,
                tmsUpdate = now
            )
            paymentOrderRepository.save(paymentOrderEntity)
            orderRepository.save(entity)
        }
    }

    override fun readPendingPaymentsOrder() {
        var orderProcessed = 0L
        logger.info("Reading order in status PENDING_PAYMENT...")
        val orderEntities = mutableListOf<OrderEntity>()
        val paymentOrderEntities = paymentOrderRepository.findByStatus(StatusOrderEnum.PENDING_PAYMENT)
        if (paymentOrderEntities.isNotEmpty()) {
            for (paymentOrderEntity in paymentOrderEntities) {
                val orderEntity = orderRepository.findByOrderCodeIgnoreCase(paymentOrderEntity.orderCode)
                if (paymentOrderEntity.status == StatusOrderEnum.PAID) {
                    orderEntity?.status = StatusOrderEnum.PAID
                    orderEntity?.tmsUpdate = LocalDateTime.now()
                    orderEntity?.let { orderEntities.add(it) }
                    orderProcessed++
                }
            }
            if (orderEntities.isNotEmpty()) orderRepository.saveAll(orderEntities)
            logger.info("Processed and PAID {} order", orderProcessed)
        } else {
            logger.info("No order found with status PENDING_PAYMENT...")
        }
    }

    override fun startAlignmentStatesOrder() {
        logger.info("Searching for orders with misaligned states CANCELLED...")
        var orderAligned = 0L
        val toUpdate = mutableListOf<it.marketplace.microservices.database.entity.PaymentOrderEntity>()
        val orderEntities = orderRepository.findByStatus(StatusOrderEnum.CANCELLED)
        val orderCodes = orderEntities.map { it.orderCode }
        if (orderEntities.isNotEmpty()) {
            orderCodes.forEach { order ->
                val entity = paymentOrderRepository.findByOrderCodeAndStatus(order, StatusOrderEnum.CANCELLED)
                if (entity != null) {
                    entity.status = StatusOrderEnum.CANCELLED
                    entity.tmsUpdate = LocalDateTime.now()
                    toUpdate.add(entity)
                }
            }
            logger.info("Aligned state in CANCELLED {} order", orderAligned)
        } else {
            logger.info("No order found with status misaligned...")
        }
        if (toUpdate.isNotEmpty()) paymentOrderRepository.saveAll(toUpdate)
    }
}
