package it.marketplace.microservices.controller.order

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.dto.OrderDto
import it.marketplace.microservices.common.resource.OrderResource
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.config.mapper.OrderMapper
import it.marketplace.microservices.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
@Tag(name = "Order API", description = "Order management")
class PostOrderController(
    private val service: OrderService
) {
    /**
     * Adds a new order.
     * @param resource the order resource to add
     * @return a response entity with a confirmation message
     * @throws ServiceException if the order cannot be added
     */
    @PostMapping("/add")
    fun save(@RequestBody resource: OrderResource): ResponseEntity<Map<String, String>> {
        service.save(OrderMapper.toDto(resource))
        return ResponseEntity.ok(mapOf("message" to "Order added!"))
    }

    /**
     * Adds multiple new orders.
     * @param resources the list of order resources to add
     * @return a response entity with a confirmation message
     * @throws ServiceException if the orders cannot be added
     */
    @PostMapping("/add-all")
    fun saveAll(@RequestBody resources: List<OrderResource>): ResponseEntity<Map<String, String>> {
        val dtos: List<OrderDto> = resources.map { OrderMapper.toDto(it) }
        service.saveAll(dtos)
        return ResponseEntity.ok(mapOf("message" to "Orders added!"))
    }
}

