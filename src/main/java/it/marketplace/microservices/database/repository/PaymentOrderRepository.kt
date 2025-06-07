package it.marketplace.microservices.database.repository

import it.marketplace.microservices.common.enums.StatusOrderEnum
import it.marketplace.microservices.database.entity.PaymentOrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository



/**
 * Repository interface for managing PaymentOrderEntity persistence operations.
 * Provides methods to find payment orders by code, status, and custom queries.
 */
@Repository
interface PaymentOrderRepository : JpaRepository<PaymentOrderEntity, Long> {
    /**
     * Finds a payment order by its code, ignoring case.
     * @param orderCode the order code
     * @return the matching PaymentOrderEntity, or null if not found
     */
    fun findByOrderCodeIgnoreCase(orderCode: String): PaymentOrderEntity

    /**
     * Finds all payment orders by a list of order codes.
     * @param orderCodes the list of order codes
     * @return a list of PaymentOrderEntity
     */
    fun findByOrderCodeIn(orderCodes: List<String>): List<PaymentOrderEntity>

    /**
     * Finds all payment orders by status.
     * @param status the payment order status
     * @return a list of PaymentOrderEntity
     */
    fun findByStatus(status: StatusOrderEnum): List<PaymentOrderEntity>

    /**
     * Finds a payment order by code where the status is not the given status.
     * @param orderCode the order code
     * @param status the status to exclude
     * @return the matching PaymentOrderEntity, or null if not found
     */
    @Query("SELECT poe FROM PaymentOrderEntity poe WHERE poe.orderCode = :orderCode AND poe.status <> :status")
    fun findByOrderCodeAndStatus(orderCode: String, status: StatusOrderEnum): PaymentOrderEntity

}


