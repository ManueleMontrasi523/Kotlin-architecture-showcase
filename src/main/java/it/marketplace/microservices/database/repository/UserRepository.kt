package it.marketplace.microservices.database.repository;

import it.marketplace.microservices.common.enums.StatusUserEnum
import it.marketplace.microservices.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Repository interface for managing UserEntity persistence operations.
 * Provides methods to find users by email, status, and update user status by email.
 */
@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    /**
     * Finds a user by email, ignoring case.
     * @param email the user's email
     * @return the matching UserEntity, or null if not found
     */
    fun findByEmailIgnoreCase(email: String): UserEntity

    /**
     * Finds all users by a list of emails, ignoring case.
     * @param email the list of user emails
     * @return a list of UserEntity
     */
    fun findAllByEmailIgnoreCaseIn(email: List<String>): List<UserEntity>

    /**
     * Finds all users by status.
     * @param status the user status
     * @return a list of UserEntity
     */
    fun findAllByStatus(status: StatusUserEnum): List<UserEntity>

    /**
     * Updates the status and update timestamp of a user by email (case-insensitive).
     * @param email the user's email
     * @param status the new status to set
     */
    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.status = :status, u.tmsUpdate = CURRENT_TIMESTAMP WHERE LOWER(u.email) = LOWER(:email)")
    fun statusRelationshipsByEmail(@Param("email") email: String, @Param("status") status: StatusUserEnum)

}
