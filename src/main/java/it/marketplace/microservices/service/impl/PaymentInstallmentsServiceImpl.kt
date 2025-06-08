package it.marketplace.microservices.service.impl

import it.marketplace.microservices.common.enums.ErrorCode
import it.marketplace.microservices.common.enums.StatusOrderEnum
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.database.entity.toDto
import it.marketplace.microservices.database.repository.PaymentInstallmentsRepository
import it.marketplace.microservices.service.PaymentInstallmentsService
import it.marketplace.microservices.service.PaymentOrderService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PaymentInstallmentsServiceImpl @Autowired constructor(
    private val repository: PaymentInstallmentsRepository,
    private val service: PaymentOrderService
) : PaymentInstallmentsService {
    private val log = LoggerFactory.getLogger(PaymentInstallmentsServiceImpl::class.java)

    override fun findAllByCode(orderCode: String) =
        repository.findByOrderCode(orderCode).map { it.toDto() }

    override fun payInstallments(orderCode: String, number: Int) {
        val entities = repository.findByOrderCodeAndStatus(orderCode, StatusOrderEnum.PENDING_PAYMENT)
        val isAllPaid = entities.size == number
        if (number > entities.size)
            throw ServiceException(
                ErrorCode.PAYMENT_RATE_EXCEED,
                "You only have ${entities.size} installments left to pay, no more"
            )
        val toUpdate = if (isAllPaid) entities else entities.take(number)
        toUpdate.forEach { entity ->
            entity.status = StatusOrderEnum.PAID
            entity.tmsUpdate = LocalDateTime.now()
        }
        repository.saveAll(toUpdate)
        if (isAllPaid) service.payOrder(orderCode, false)
    }
}

