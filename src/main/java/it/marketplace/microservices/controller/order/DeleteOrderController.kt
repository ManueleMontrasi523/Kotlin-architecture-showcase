package it.marketplace.microservices.controller.order

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller for deleting orders in the marketplace system.
 * Provides an endpoint to delete an order by its code.
 */
@RestController
@RequestMapping("/order")
@Tag(name = "Order API", description = "Order management")
class DeleteOrderController(
    private val service: OrderService
) {
    @DeleteMapping("/delete")
    fun delete(@RequestParam("orderCode") code: String): ResponseEntity<Map<String, String>> {
        service.deleteByCode(code)
        return ResponseEntity.ok(mapOf("message" to "Order deleted!"))
    }
}

