package it.marketplace.microservices.database.repository

import it.marketplace.microservices.common.enums.StatusOrderEnum
import it.marketplace.microservices.database.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * Repository interface for managing OrderEntity persistence operations.
 * Provides methods to find orders by code, user email, and status.
 */
@Repository
interface OrderRepository : JpaRepository<OrderEntity, Long> {
    /**
     * Finds an order by its code, ignoring case.
     * @param orderCode the order code
     * @return the matching OrderEntity, or null if not found
     */
    fun findByOrderCodeIgnoreCase(orderCode: String): OrderEntity?

    /**
     * Finds an order by user email and status.
     * @param email the user's email
     * @param status the order status
     * @return the matching OrderEntity, or null if not found
     */
    @Query("SELECT oe FROM OrderEntity oe WHERE user.email = :email AND status = :status")
    fun findOrderByUserMailAndStatus(email: String, status: StatusOrderEnum): OrderEntity?

    /**
     * Finds all orders by user email.
     * @param email the user's email
     * @return a list of OrderEntity
     */
    @Query("SELECT oe FROM OrderEntity oe WHERE user.email = :email")
    fun findOrderByUserMail(email: String): List<OrderEntity>

    /**
     * Finds all orders by status.
     * @param status the order status
     * @return a list of OrderEntity
     */
    fun findByStatus(status: StatusOrderEnum): List<OrderEntity>
}

