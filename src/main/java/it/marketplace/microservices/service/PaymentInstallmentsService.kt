package it.marketplace.microservices.service

import PaymentInstallmentsDto

/**
 * Service interface for managing payment installments in the marketplace system.
 * Provides methods for retrieving and paying order installments.
 */
interface PaymentInstallmentsService {
    /**
     * Retrieves all payment installments for a given order code.
     * @param orderCode the order code
     * @return a list of PaymentInstallmentsDto
     */
    fun findAllByCode(orderCode: String): List<PaymentInstallmentsDto>

    /**
     * Pays a specified number of installments for an order.
     * @param orderCode the order code
     * @param number the number of installments to pay
     */
    fun payInstallments(orderCode: String, number: Int)
}

