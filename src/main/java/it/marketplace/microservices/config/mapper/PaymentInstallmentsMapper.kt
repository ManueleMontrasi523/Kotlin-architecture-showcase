package it.marketplace.microservices.config.mapper

import PaymentInstallmentsDto
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import it.marketplace.microservices.common.resource.PaymentInstallmentsResource
import it.marketplace.microservices.database.entity.PaymentInstallmentsEntity

/**
 * Mapper class for converting between PaymentInstallments DTOs, resources, and entities in the marketplace system.
 * Uses Jackson ObjectMapper for object conversion.
 */
object PaymentInstallmentsMapper {
    private val mapper: ObjectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    /**
     * Converts a PaymentInstallmentsDto to a PaymentInstallmentsResource.
     * @param dto the PaymentInstallmentsDto to convert
     * @return the corresponding PaymentInstallmentsResource
     */
    fun toResource(dto: PaymentInstallmentsDto?): PaymentInstallmentsResource? {
        if (dto == null) return null
        return mapper.convertValue(dto, PaymentInstallmentsResource::class.java)
    }

    /**
     * Converts a PaymentInstallmentsResource to a PaymentInstallmentsDto.
     * @param resource the PaymentInstallmentsResource to convert
     * @return the corresponding PaymentInstallmentsDto
     */
    fun toDto(resource: PaymentInstallmentsResource?): PaymentInstallmentsDto? {
        if (resource == null) return null
        return mapper.convertValue(resource, PaymentInstallmentsDto::class.java)
    }

    /**
     * Converts a PaymentInstallmentsEntity to a PaymentInstallmentsDto.
     * @param entity the PaymentInstallmentsEntity to convert
     * @return the corresponding PaymentInstallmentsDto
     */
    fun toDto(entity: PaymentInstallmentsEntity?): PaymentInstallmentsDto? {
        if (entity == null) return null
        return mapper.convertValue(entity, PaymentInstallmentsDto::class.java)
    }

    /**
     * Converts a PaymentInstallmentsDto to a PaymentInstallmentsEntity.
     * @param dto the PaymentInstallmentsDto to convert
     * @return the corresponding PaymentInstallmentsEntity
     */
    fun toEntity(dto: PaymentInstallmentsDto?): PaymentInstallmentsEntity? {
        if (dto == null) return null
        return mapper.convertValue(dto, PaymentInstallmentsEntity::class.java)
    }
}

