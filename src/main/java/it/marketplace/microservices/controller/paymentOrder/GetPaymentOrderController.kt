package it.marketplace.microservices.controller.paymentOrder

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.resource.PaymentOrderResource
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.config.mapper.PaymentOrderMapper
import it.marketplace.microservices.service.PaymentOrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment-order")
@Tag(name = "Payment Order API", description = "Payment Order management")
class GetPaymentOrderController(
    private val service: PaymentOrderService
) {
    /**
     * Retrieves all payment orders for a given user email.
     * @param email the user email to retrieve payment orders for
     * @return a response entity containing a list of payment order resources
     * @throws ServiceException if the payment orders cannot be retrieved
     */
    @GetMapping("/get-by-user")
    fun find(@RequestParam("email") email: String): ResponseEntity<List<PaymentOrderResource?>?> {
        val resources = service.findOrderByEmail(email).map { PaymentOrderMapper.toResource(it) }
        return ResponseEntity.ok(resources)
    }

    /**
     * Retrieves all payment orders.
     * @return a response entity containing a list of payment order resources
     * @throws ServiceException if the payment orders cannot be retrieved
     */
    @GetMapping("/get-all")
    fun findAll(): ResponseEntity<List<PaymentOrderResource?>?> {
        val resources = service.findAll().map { PaymentOrderMapper.toResource(it) }
        return ResponseEntity.ok(resources)
    }
}

