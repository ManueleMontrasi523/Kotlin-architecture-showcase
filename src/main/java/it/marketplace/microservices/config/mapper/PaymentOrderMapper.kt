package it.marketplace.microservices.config.mapper

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import it.marketplace.microservices.common.dto.PaymentOrderDto
import it.marketplace.microservices.common.resource.PaymentOrderResource
import it.marketplace.microservices.database.entity.PaymentOrderEntity

/**
 * Mapper class for converting between PaymentOrder DTOs, resources, and entities in the marketplace system.
 * Uses Jackson ObjectMapper for object conversion.
 */
object PaymentOrderMapper {
    private val mapper: ObjectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    /**
     * Converts a PaymentOrderDto to a PaymentOrderResource.
     * @param dto the PaymentOrderDto to convert
     * @return the corresponding PaymentOrderResource
     */
    fun toResource(dto: PaymentOrderDto?): PaymentOrderResource? {
        if (dto == null) return null
        return mapper.convertValue(dto, PaymentOrderResource::class.java)
    }

    /**
     * Converts a PaymentOrderResource to a PaymentOrderDto.
     * @param resource the PaymentOrderResource to convert
     * @return the corresponding PaymentOrderDto
     */
    fun toDto(resource: PaymentOrderResource?): PaymentOrderDto? {
        if (resource == null) return null
        return mapper.convertValue(resource, PaymentOrderDto::class.java)
    }

    /**
     * Converts a PaymentOrderEntity to a PaymentOrderDto.
     * @param entity the PaymentOrderEntity to convert
     * @return the corresponding PaymentOrderDto
     */
    fun toDto(entity: PaymentOrderEntity?): PaymentOrderDto? {
        if (entity == null) return null
        return mapper.convertValue(entity, PaymentOrderDto::class.java)
    }

    /**
     * Converts a PaymentOrderDto to a PaymentOrderEntity.
     * @param dto the PaymentOrderDto to convert
     * @return the corresponding PaymentOrderEntity
     */
    fun toEntity(dto: PaymentOrderDto?): PaymentOrderEntity? {
        if (dto == null) return null
        return mapper.convertValue(dto, PaymentOrderEntity::class.java)
    }
}

