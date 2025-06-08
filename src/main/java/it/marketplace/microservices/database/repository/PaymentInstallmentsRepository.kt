package it.marketplace.microservices.database.repository

import it.marketplace.microservices.common.enums.StatusOrderEnum
import it.marketplace.microservices.database.entity.PaymentInstallmentsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/**
 * Repository interface for managing PaymentInstallmentsEntity persistence operations.
 * Provides methods to find payment installments by order code and status.
 */
@Repository
interface PaymentInstallmentsRepository : JpaRepository<PaymentInstallmentsEntity, Long> {
    /**
     * Finds all payment installments by order code.
     * @param orderCode the order code
     * @return a list of PaymentInstallmentsEntity
     */
    fun findByOrderCode(orderCode: String): List<PaymentInstallmentsEntity>

    /**
     * Finds all payment installments by order code and status.
     * @param orderCode the order code
     * @param statusOrderEnum the status of the payment installment
     * @return a list of PaymentInstallmentsEntity
     */
    fun findByOrderCodeAndStatus(orderCode: String, statusOrderEnum: StatusOrderEnum): List<PaymentInstallmentsEntity>
}
