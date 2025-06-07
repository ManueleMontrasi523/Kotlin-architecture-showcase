package it.marketplace.microservices.controller.order

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.resource.OrderResource
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.config.mapper.OrderMapper.toDto
import it.marketplace.microservices.service.OrderService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
@Tag(name = "Order API", description = "Order management")
class PutOrderController(
    private val service: OrderService
) {
    /**
     * Updates an existing order.
     * @param resource the order resource to update
     * @return a response entity with a confirmation message
     * @throws ServiceException if the order cannot be updated
     */
    @PutMapping("/update")
    fun update(@Valid @RequestBody resource: OrderResource): ResponseEntity<Map<String, String>> {
        service.update(toDto(resource))
        return ResponseEntity.ok(mapOf("message" to "Order Updated!"))
    }

    /**
     * Cancels an order by its code.
     * @param code the order code to cancel
     * @return a response entity with a confirmation message
     * @throws ServiceException if the order cannot be cancelled
     */
    @PutMapping("/cancel")
    fun cancel(@RequestParam("orderCode") code: String): ResponseEntity<Map<String, String>> {
        service.cancel(code)
        return ResponseEntity.ok(mapOf("message" to "Order Cancelled!"))
    }
}

