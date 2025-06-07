package it.marketplace.microservices.service

import it.marketplace.microservices.common.dto.UserDto
import it.marketplace.microservices.common.enums.StatusUserEnum
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.database.entity.UserEntity

/**
 * Service interface for managing users in the marketplace system.
 * Provides methods for user creation, update, deletion, status changes, and retrieval operations.
 */
interface UserService {
    /**
     * Saves a new user.
     * @param dto the user DTO to save
     * @throws ServiceException if the user already exists or another error occurs
     */
    @Throws(ServiceException::class)
    fun save(dto: UserDto)

    /**
     * Saves a list of users.
     * @param dtos the list of user DTOs to save
     */
    fun saveAll(dtos: List<UserDto>)

    /**
     * Finds a user by email.
     * @param email the email of the user to find
     * @return the matching UserDto
     * @throws ServiceException if the user is not found
     */
    @Throws(ServiceException::class)
    fun findByEmail(email: String): UserDto

    /**
     * Finds a user entity by email.
     * @param email the email of the user to find
     * @return the matching UserEntity
     * @throws ServiceException if the user is not found
     */
    @Throws(ServiceException::class)
    fun findByEmailEntity(email: String): UserEntity

    /**
     * Finds all users by status.
     * @param status the status to filter users
     * @return a list of UserDto
     * @throws ServiceException if an error occurs
     */
    @Throws(ServiceException::class)
    fun findAll(status: StatusUserEnum): List<UserDto>

    /**
     * Updates an existing user.
     * @param dto the user DTO with updated data
     * @throws ServiceException if the user is not found or another error occurs
     */
    @Throws(ServiceException::class)
    fun update(dto: UserDto)

    /**
     * Deletes a user by email.
     * @param email the email of the user to delete
     * @throws ServiceException if the user is not found or another error occurs
     */
    @Throws(ServiceException::class)
    fun deleteByEmail(email: String)

    /**
     * Updates the status of a user by email.
     * @param email the email of the user to update
     * @param status the new status to set
     * @throws ServiceException if the user is not found or another error occurs
     */
    @Throws(ServiceException::class)
    fun statusByEmail(email: String, status: StatusUserEnum)
}

