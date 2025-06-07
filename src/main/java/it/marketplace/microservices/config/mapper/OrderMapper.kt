package it.marketplace.microservices.config.mapper

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import it.marketplace.microservices.common.dto.OrderDto
import it.marketplace.microservices.common.dto.ProductOrderDto
import it.marketplace.microservices.common.dto.UserDto
import it.marketplace.microservices.common.resource.OrderResource
import it.marketplace.microservices.common.resource.ProductOrderResource
import it.marketplace.microservices.common.resource.UserResource
import it.marketplace.microservices.config.mapper.ProductOrderMapper.toResource
import it.marketplace.microservices.database.entity.OrderEntity
import it.marketplace.microservices.database.entity.ProductOrderEntity
import it.marketplace.microservices.database.entity.UserEntity
import java.util.List

/**
 * Mapper class for converting between Order DTOs, resources, and entities in the marketplace system.
 * Uses Jackson ObjectMapper for object conversion and handles nested user and product order mappings.
 */
object OrderMapper {
    private var mapper: ObjectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(JavaTimeModule())
        .registerModule(Hibernate6Module())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    /**
     * Converts an OrderDto to an OrderResource, including nested user and product orders.
     */
    fun toResource(dto: OrderDto?): OrderResource {
        val resource = mapper.convertValue(dto, OrderResource::class.java)
        dto?.user.let {
            resource.userResource = mapper.convertValue(it, UserResource::class.java)
        }
//        dto.productOrder?.stream()?.map { toResource(it) }
//            ?.let { resource.productResource = it.toList() as List<*> as List<ProductOrderResource> }
//            ?
        return resource
    }

    /**
     * Converts an OrderResource to an OrderDto, including nested user and product orders.
     */
    fun toDto(resource: OrderResource?): OrderDto {
        val dto = mapper.convertValue(resource, OrderDto::class.java)
        resource?.userResource.let {
            dto.user = mapper.convertValue(it, UserDto::class.java)
        }
//        resource?.productResource?.stream()?.map { ProductOrderMapper.toResource(it) }
//            ?.let { resource.productResource = it.toList() as List<*> as List<ProductOrderResource> }

        return dto
    }

    /**
     * Converts an OrderEntity to an OrderDto, including nested user and product orders.
     */
    fun toDto(entity: OrderEntity?): OrderDto {
        val dto = mapper.convertValue(entity, OrderDto::class.java)
        entity?.user?.let {
            dto.user = mapper.convertValue(it, UserDto::class.java)
        }
        dto.productOrder = entity?.productOrder?.map { ProductOrderMapper.toDto(it) } ?: emptyList()
        return dto
    }

    /**
     * Converts an OrderDto to an OrderEntity, including nested user and product orders.
     */
    fun toEntity(dto: OrderDto?): OrderEntity {
        val entity = mapper.convertValue(dto, OrderEntity::class.java)
        dto?.user.let {
            entity.user = mapper.convertValue(it, UserEntity::class.java)
        }
        dto?.productOrder.let { products ->
            entity.productOrder = products?.map { mapper.convertValue(it, ProductOrderEntity::class.java) }!!
        }
        return entity
    }
}
