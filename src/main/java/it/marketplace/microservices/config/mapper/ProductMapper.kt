package it.marketplace.microservices.config.mapper

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import it.marketplace.microservices.common.dto.ProductDto
import it.marketplace.microservices.common.resource.ProductResource
import it.marketplace.microservices.database.entity.ProductEntity

/**
 * Mapper class for converting between Product DTOs, resources, and entities in the marketplace system.
 * Uses Jackson ObjectMapper for object conversion.
 */
object ProductMapper {
    private val mapper: ObjectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    /**
     * Converts a ProductDto to a ProductResource.
     * @param dto the ProductDto to convert
     * @return the corresponding ProductResource
     */
    fun toResource(dto: ProductDto): ProductResource {
        return mapper.convertValue(dto, ProductResource::class.java)
    }

    /**
     * Converts a ProductResource to a ProductDto.
     * @param resource the ProductResource to convert
     * @return the corresponding ProductDto
     */
    fun toDto(resource: ProductResource): ProductDto {
        return mapper.convertValue(resource, ProductDto::class.java)
    }

    /**
     * Converts a ProductEntity to a ProductDto.
     * @param entity the ProductEntity to convert
     * @return the corresponding ProductDto
     */
    fun toDto(entity: ProductEntity): ProductDto {
        return mapper.convertValue(entity, ProductDto::class.java)
    }

    /**
     * Converts a ProductDto to a ProductEntity.
     * @param dto the ProductDto to convert
     * @return the corresponding ProductEntity
     */
    fun toEntity(dto: ProductDto): ProductEntity {
        return mapper.convertValue(dto, ProductEntity::class.java)
    }
}

