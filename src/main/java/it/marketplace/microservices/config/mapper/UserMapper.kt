package it.marketplace.microservices.config.mapper

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import it.marketplace.microservices.common.dto.UserDto
import it.marketplace.microservices.common.resource.UserResource
import it.marketplace.microservices.database.entity.UserEntity

/**
 * Mapper class for converting between User DTOs, resources, and entities in the marketplace system.
 * Uses Jackson ObjectMapper for object conversion and ensures email is stored in lowercase.
 */
object UserMapper {
    private val mapper: ObjectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    /**
     * Converts a UserDto to a UserResource.
     * @param dto the UserDto to convert
     * @return the corresponding UserResource
     */
    fun toResource(dto: UserDto): UserResource {
        return mapper.convertValue(dto, UserResource::class.java)
    }

    /**
     * Converts a UserResource to a UserDto.
     * @param resource the UserResource to convert
     * @return the corresponding UserDto
     */
    fun toDto(resource: UserResource): UserDto {
        return mapper.convertValue(resource, UserDto::class.java)
    }

    /**
     * Converts a UserEntity to a UserDto.
     * @param entity the UserEntity to convert
     * @return the corresponding UserDto
     */
    fun toDto(entity: UserEntity): UserDto {
        return mapper.convertValue(entity, UserDto::class.java)
    }

    /**
     * Converts a UserDto to a UserEntity and ensures the email is lowercase.
     * @param dto the UserDto to convert
     * @return the corresponding UserEntity
     */
    fun toEntity(dto: UserDto): UserEntity {
        val entity: UserEntity = mapper.convertValue(dto, UserEntity::class.java)
        entity.email = entity.email.lowercase()
        return entity
    }
}

