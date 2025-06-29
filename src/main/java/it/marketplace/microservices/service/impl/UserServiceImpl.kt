package it.marketplace.microservices.service.impl

import it.marketplace.microservices.common.dto.UserDto
import it.marketplace.microservices.common.dto.toEntity
import it.marketplace.microservices.common.enums.ErrorCode
import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.database.entity.UserEntity
import it.marketplace.microservices.database.entity.toDto
import it.marketplace.microservices.database.repository.UserRepository
import it.marketplace.microservices.service.UserService
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

            dto.role = RoleEnum.CLIENT
            dto.status = StatusUserEnum.ACTIVE
            dto.tmsSubscriptionDate = LocalDateTime.now()
            dto.tmsUpdate = LocalDateTime.now()

            repository.save(dto.toEntity())
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
                dto.role = RoleEnum.CLIENT
                dto.status = StatusUserEnum.ACTIVE
                dto.tmsSubscriptionDate = now
                dto.tmsUpdate = now
                dto.toEntity()
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
    override fun findByEmail(email: String): UserDto? {
        return checkIfUserExist(email)?.toDto()
    }

    /**
     * Finds a user entity by email.
     * @param email the email of the user to find
     * @return the matching UserEntity
     * @throws ServiceException if the user is not found
     */
    override fun findByEmailEntity(email: String?): UserEntity? {
        return checkIfUserExist(email)
    }

    /**
     * Finds all users by status.
     * @param status the status to filter users
     * @return a list of UserDto
     */
    override fun findByStatus(status: StatusUserEnum?): List<UserDto> {
        var entities: List<UserEntity>
        if (status == null) {
            entities = repository.findAll()
        } else {
            entities = repository.findAllByStatus(status)
        }
        return entities.map { it.toDto() }
    }

    /**
     * Finds all users by status.
     * @return a list of UserDto
     */
    override fun findAll(): List<UserDto> {
        return repository.findAll().map { it.toDto() }
    }


    /**
     * Updates an existing user.
     * @param dto the user DTO with updated data
     * @throws ServiceException if the user is not found or another error occurs
     */
    @Throws(ServiceException::class)
    override fun update(dto: UserDto) {
        val entity = checkIfUserExist(dto.email)!!

        copyNonNullProperties(dto, entity)
        repository.save(entity)
    }

    /**
     * Deletes a user by email.
     * @param email the email of the user to delete
     * @throws ServiceException if the user is not found or another error occurs
     */
    @Throws(ServiceException::class)
    override fun deleteByEmail(email: String) {
        try {
            val entity = checkIfUserExist(email)
            repository.deleteById(entity?.id)
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
    private fun checkIfUserExist(email: String?): UserEntity? {
        val entity = repository.findByEmailIgnoreCase(email)
        if (Objects.isNull(entity)) throw ServiceException(
            ErrorCode.EMAIL_NOT_FOUND,
            "User with email: $email not found"
        )
        return entity
    }

    /**
     * Update only non-nullable dto properties on the entity.
     * Note: only rejectReason is nullable in OrderDto, so only this field is handled.
     * If there are other nullable fields in the future, add them here.
     */
    private fun copyNonNullProperties(dto: UserDto, entity: UserEntity) {
        dto.name?.takeIf { it.isNotBlank() }?.let { entity.name = it }
        dto.lastname?.takeIf { it.isNotBlank() }?.let { entity.lastname = it }
        dto.email?.takeIf { it.isNotBlank() }?.let { entity.email = it }
        dto.residenceAddress?.takeIf { it.isNotBlank() }?.let { entity.residenceAddress = it }
        dto.residenceCity?.takeIf { it.isNotBlank() }?.let { entity.residenceCity = it }
        dto.role?.let { entity.role = it }
        dto.status?.let { entity.status = it }
        dto.tmsSubscriptionDate?.let { entity.tmsSubscriptionDate = it }

        entity.tmsUpdate = LocalDateTime.now()
    }
}
