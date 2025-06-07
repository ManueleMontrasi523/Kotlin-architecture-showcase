package it.marketplace.microservices.service

import it.marketplace.microservices.common.dto.OrderDto
import it.marketplace.microservices.config.exception.ServiceException

/**
 * Service interface for managing orders in the marketplace system.
 * Provides methods for order creation, update, deletion, payment, and retrieval operations.
 */
interface OrderService {
    /**
     * Saves a new order.
     * @param dto the order DTO to save
     * @throws ServiceException if an error occurs
     */
    @Throws(ServiceException::class)
    fun save(dto: OrderDto)

    /**
     * Saves an order directly without additional processing.
     * @param dto the order DTO to save
     * @throws ServiceException if an error occurs
     */
    @Throws(ServiceException::class)
    fun saveDirectly(dto: OrderDto)

    /**
     * Saves a list of orders.
     * @param dtos the list of order DTOs to save
     */
    fun saveAll(dtos: List<OrderDto>)

    /**
     * Finds an order by its code.
     * @param code the order code
     * @return the matching OrderDto
     * @throws ServiceException if the order is not found
     */
    @Throws(ServiceException::class)
    fun findByCode(code: String): OrderDto


    /**
     * Finds all orders in the system.
     * @return a list of OrderDto
     * @throws ServiceException if an error occurs
     */
    @Throws(ServiceException::class)
    fun findAll(): List<OrderDto?>

    /**
     * Updates an existing order.
     * @param dto the order DTO with updated data
     * @throws ServiceException if the order is not found or another error occurs
     */
    @Throws(ServiceException::class)
    fun update(dto: OrderDto)

    /**
     * Deletes an order by its code.
     * @param code the order code to delete
     * @throws ServiceException if the order is not found or another error occurs
     */
    @Throws(ServiceException::class)
    fun deleteByCode(code: String)


    /**
     * Finds all orders for a user by email.
     * @param email the user's email
     * @return a list of OrderDto
     * @throws ServiceException if an error occurs
     */
    @Throws(ServiceException::class)
    fun findByUserMail(email: String): List<OrderDto>

    /**
     * Cancels an order by its code.
     * @param code the order code to cancel
     */
    fun cancel(code: String)

    /**
     * Marks an order as paid by its code.
     * @param orderCode the order code to mark as paid
     */
    fun payOrder(orderCode: String)
}

