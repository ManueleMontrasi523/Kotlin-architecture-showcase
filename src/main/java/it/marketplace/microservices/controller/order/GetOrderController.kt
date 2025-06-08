package it.marketplace.microservices.controller.order

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.dto.toResource
import it.marketplace.microservices.common.resource.OrderResource
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
@Tag(name = "Order API", description = "Order management")
class GetOrderController(
    private val service: OrderService
) {
    /**
     * Retrieves an order by its code.
     *
     * @param code the order code to retrieve
     * @return a response entity containing the order resource
     * @throws ServiceException if the order cannot be found
     */
    @GetMapping("/get-by-code")
    fun find(@RequestParam("orderCode") code: String): ResponseEntity<OrderResource> {
        return ResponseEntity.ok(service.findByCode(code).toResource())
    }

    /**
     * Retrieves all orders.
     *
     * @return a response entity containing a list of order resources
     * @throws ServiceException if the orders cannot be retrieved
     */
    @GetMapping("/get-all")
    fun findAll(): ResponseEntity<List<OrderResource?>?> {
        return ResponseEntity.ok(service.findAll().map { it?.toResource() })
    }
}
