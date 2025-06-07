package it.marketplace.microservices.config.mapper

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import it.marketplace.microservices.common.dto.ProductOrderDto
import it.marketplace.microservices.common.resource.ProductOrderResource
import it.marketplace.microservices.database.entity.ProductOrderEntity

/**
 * Mapper class for converting between ProductOrder DTOs, resources, and entities in the marketplace system.
 * Uses Jackson ObjectMapper for object conversion.
 */
object ProductOrderMapper {
    private val mapper: ObjectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    /**
     * Converts a ProductOrderDto to a ProductOrderResource.
     * @param dto the ProductOrderDto to convert
     * @return the corresponding ProductOrderResource
     */
    fun toResource(dto: ProductOrderDto?): ProductOrderResource? {
        if (dto == null) return null
        return mapper.convertValue(dto, ProductOrderResource::class.java)
    }

    /**
     * Converts a ProductOrderResource to a ProductOrderDto.
     * @param resource the ProductOrderResource to convert
     * @return the corresponding ProductOrderDto
     */
    fun toDto(resource: ProductOrderResource?): ProductOrderDto? {
        if (resource == null) return null
        return mapper.convertValue(resource, ProductOrderDto::class.java)
    }

    /**
     * Converts a ProductOrderEntity to a ProductOrderDto.
     * @param entity the ProductOrderEntity to convert
     * @return the corresponding ProductOrderDto
     */
    fun toDto(entity: ProductOrderEntity?): ProductOrderDto {
        return mapper.convertValue(entity, ProductOrderDto::class.java)
    }

    /**
     * Converts a ProductOrderDto to a ProductOrderEntity.
     * @param dto the ProductOrderDto to convert
     * @return the corresponding ProductOrderEntity
     */
    fun toEntity(dto: ProductOrderDto?): ProductOrderEntity? {
        if (dto == null) return null
        return mapper.convertValue(dto, ProductOrderEntity::class.java)
    }
}

