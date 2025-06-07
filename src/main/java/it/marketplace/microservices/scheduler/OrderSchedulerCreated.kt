package it.marketplace.microservices.scheduler

import it.marketplace.microservices.service.TransactionService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * Scheduler component for periodically processing and aligning order states in the marketplace system.
 * Triggers background jobs to read pending payments and align order statuses at a fixed interval.
 */
@Component
class OrderSchedulerCreated(
    private val service: TransactionService
) {
    /**
     * Scheduled task that processes pending payments and aligns order states every minute.
     */
    @Scheduled(fixedDelay = 60000)
    fun startProcessOrder() {
        service.readPendingPaymentsOrder()
        service.startAlignmentStatesOrder()
    }
}

