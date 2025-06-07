package it.marketplace.microservices.service.impl

import it.marketplace.microservices.common.dto.UserDto
import it.marketplace.microservices.common.enums.ErrorCode
import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.config.mapper.UserMapper.toDto
import it.marketplace.microservices.config.mapper.UserMapper.toEntity
import it.marketplace.microservices.database.entity.UserEntity
import it.marketplace.microservices.database.repository.UserRepository
import it.marketplace.microservices.service.UserService
import it.marketplace.microservices.utils.CopyProperties
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UserServiceImpl(
    private val repository: UserRepository
) : UserService {
    private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    /**
     * Saves a new user.
     * @param dto the user DTO to save
     * @throws ServiceException if the user already exists or another error occurs
     */
    override fun save(dto: UserDto) {
        try {
            val entityOld = repository.findByEmailIgnoreCase(dto.email)
            if (entityOld != null) throw ServiceException(
                ErrorCode.DATA_ALREADY_PRESENT,
                "Email already registered"
            )

            val entity = toEntity(dto)
            entity.role = RoleEnum.CLIENT
            entity.status = StatusUserEnum.ACTIVE
            entity.tmsSubscriptionDate = dto.tmsSubscriptionDate
            entity.tmsUpdate = LocalDateTime.now()

            repository.save(entity)
        } catch (e: ServiceException) {
            logger.error("ERROR in the class " + this::class.java.name + " with error ${e.fillInStackTrace()}")
            throw ServiceException(ErrorCode.GENERIC_ERROR, e.message)
        }
    }

    /**
     * Saves a list of users.
     * @param dto the list of user DTOs to save
     * @throws ServiceException if any user already exists or another error occurs
     */
    override fun saveAll(dtos: List<UserDto>) {
        try {
            val now = LocalDateTime.now()
            val entities = dtos.map { dto ->
                val entity = toEntity(dto)
                entity.role = RoleEnum.CLIENT
                entity.status = StatusUserEnum.ACTIVE
                entity.tmsSubscriptionDate = now
                entity.tmsUpdate = now
                entity
            }
            repository.saveAll(entities)
        } catch (e: ServiceException) {
            logger.error("ERROR in the class " + this::class.java.name + " with error ${e.fillInStackTrace()}")
            throw ServiceException(
                ErrorCode.GENERIC_ERROR,
                e.message
            )
        }
    }

    /**
     * Finds a user by email.
     * @param email the email of the user to find
     * @return the matching UserDto
     * @throws ServiceException if the user is not found
     */
    override fun findByEmail(email: String): UserDto {
        val entity = checkIfUserExist(email)
        return toDto(entity)
    }

    /**
     * Finds a user entity by email.
     * @param email the email of the user to find
     * @return the matching UserEntity
     * @throws ServiceException if the user is not found
     */
    override fun findByEmailEntity(email: String): UserEntity {
        return checkIfUserExist(email)
    }

    /**
     * Finds all users by status.
     * @param status the status to filter users
     * @return a list of UserDto
     */
    override fun findAll(status: StatusUserEnum): List<UserDto> {
        val entities = repository.findAllByStatus(status)
        return entities.map { toDto(it) }
    }


    /**
     * Updates an existing user.
     * @param dto the user DTO with updated data
     * @throws ServiceException if the user is not found or another error occurs
     */
    @Throws(ServiceException::class)
    override fun update(dto: UserDto) {
        val entity: UserEntity = checkIfUserExist(dto.email)

        CopyProperties.copyNonNullProperties(dto, entity)
        entity.setTmsUpdate(LocalDateTime.now())
        repository.save<UserEntity>(entity)
    }

    /**
     * Deletes a user by email.
     * @param email the email of the user to delete
     * @throws ServiceException if the user is not found or another error occurs
     */
    @Throws(ServiceException::class)
    override fun deleteByEmail(email: String) {
        try {
            val entity: UserEntity = checkIfUserExist(email)
            repository.deleteById(entity.getId())
        } catch (e: ServiceException) {
            throw ServiceException(ErrorCode.GENERIC_ERROR, e.message)
        }
    }

    /**
     * Updates the status of a user by email.
     * @param email the email of the user to update
     * @param status the new status to set
     * @throws ServiceException if the user is not found or another error occurs
     */
    @Throws(ServiceException::class)
    override fun statusByEmail(email: String, status: StatusUserEnum) {
        checkIfUserExist(email)
        repository.statusRelationshipsByEmail(email, status)
    }

    /**
     * Checks if a user exists by email, throws exception if not found.
     * @param email the email to check
     * @return the matching UserEntity
     * @throws ServiceException if the user is not found
     */
    @Throws(ServiceException::class)
    private fun checkIfUserExist(email: String): UserEntity {
        val entity = repository.findByEmailIgnoreCase(email)
        if (Objects.isNull(entity)) throw ServiceException(
            ErrorCode.EMAIL_NOT_FOUND,
            "User with email: $email not found"
        )
        return entity
    }
}
