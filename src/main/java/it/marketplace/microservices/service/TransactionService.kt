package it.marketplace.microservices.service

/**
 * Service interface for managing transactions in the marketplace system.
 * Provides methods for processing orders, handling pending payments, and aligning order/payment states.
 */
interface TransactionService {
    /**
     * Starts processing for a new order, updating its status and triggering background jobs.
     * @param orderCode the order code to process
     */
    fun startProcessing(orderCode: String)

    /**
     * Starts processing for a pending payment, updating order and payment order status.
     * @param message the message map containing order code and debit
     */
    fun startPendingPayment(message: Map<String, String>)

    /**
     * Reads all orders in PENDING_PAYMENT status and updates their state if paid.
     */
    fun readPendingPaymentsOrder()

    /**
     * Aligns the states of orders and payment orders marked as CANCELLED.
     */
    fun startAlignmentStatesOrder()
}

