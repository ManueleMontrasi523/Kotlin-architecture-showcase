package it.marketplace.microservices.service

import it.marketplace.microservices.common.dto.PaymentOrderDto

/**
 * Service interface for managing payment orders in the marketplace system.
 * Provides methods for retrieving, paying, and listing payment orders.
 */
interface PaymentOrderService {
    /**
     * Retrieves all payment orders for a given user email.
     * @param email the user email
     * @return a list of PaymentOrderDto
     */
    fun findOrderByEmail(email: String): List<PaymentOrderDto>

    /**
     * Retrieves all payment orders in the system.
     * @return a list of PaymentOrderDto
     */
    fun findAll(): List<PaymentOrderDto>

    /**
     * Pays a payment order, updating its status and creating installments if needed.
     * @param orderCode the order code to pay
     * @param isInstallments whether the payment is in installments
     */
    fun payOrder(orderCode: String, isInstallments: Boolean)
}

