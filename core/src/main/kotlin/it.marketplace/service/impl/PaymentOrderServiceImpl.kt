package it.marketplace.service.impl

import it.marketplace.common.dto.OrderDto
import it.marketplace.common.dto.PaymentOrderDto
import it.marketplace.common.enums.StatusOrderEnum
import it.marketplace.database.entity.PaymentInstallmentsEntity
import it.marketplace.database.entity.toDto
import it.marketplace.database.repository.PaymentInstallmentsRepository
import it.marketplace.database.repository.PaymentOrderRepository
import it.marketplace.service.OrderService
import it.marketplace.service.PaymentOrderService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PaymentOrderServiceImpl @Autowired constructor(
    private val repository: PaymentOrderRepository,
    private val paymentInstallmentsRepository: PaymentInstallmentsRepository,
    private val orderService: OrderService
) : PaymentOrderService {
    private val log = LoggerFactory.getLogger(PaymentOrderServiceImpl::class.java)

    override fun findOrderByEmail(email: String): List<PaymentOrderDto> {
        val orders: List<OrderDto> = orderService.findByUserMail(email)
        val orderCodes = if (orders.isNotEmpty()) orders.map { it.orderCode }.toList() else null
        return if (orderCodes != null) {
            repository.findByOrderCodeIn(orderCodes).map { it.toDto() }
        } else {
            emptyList()
        }
    }

    override fun findAll(): List<PaymentOrderDto> =
        repository.findAll().map { it.toDto() }

    override fun payOrder(orderCode: String, isInstallments: Boolean) {
        log.info("Paid order {} with installments {}", orderCode, isInstallments)
        val entity = repository.findByOrderCodeIgnoreCase(orderCode)
        if (isInstallments) {
            entity.status = StatusOrderEnum.RATEIZED
            payWithInstallments(orderCode, entity.debit)
        } else {
            entity.status = StatusOrderEnum.PAID
            orderService.payOrder(orderCode)
        }
        entity.tmsUpdate = LocalDateTime.now()
        repository.save(entity)
    }

    private fun payWithInstallments(orderCode: String, debit: Double) {
        val payEntities = mutableListOf<PaymentInstallmentsEntity>()
        val miniDebit = debit / 12
        for (i in 1..12) {
            val installment = PaymentInstallmentsEntity(
                reference = "RATE_$i",
                orderCode = orderCode,
                status = StatusOrderEnum.PENDING_PAYMENT,
                debit = miniDebit,
                tmsUpdate = LocalDateTime.now()
            )
            payEntities.add(installment)
        }
        paymentInstallmentsRepository.saveAll(payEntities)
    }
}

