package it.marketplace.microservices.controller.paymentOrder

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.PaymentOrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment-order")
@Tag(name = "Payment Order API", description = "Payment Order management")
class PutPaymentOrderController(
    private val service: PaymentOrderService
) {
    /**
     * Pays an order, specifying if it is in installments.
     * @param orderCode the order code to pay
     * @param isInstallments whether the payment is for installments
     * @return a response entity with a confirmation message
     * @throws ServiceException if the payment cannot be processed
     */
    @PutMapping("/pay")
    fun pay(
        @RequestParam("orderCode") orderCode: String,
        @RequestParam("isInstallments") isInstallments: Boolean
    ): ResponseEntity<Map<String, String>> {
        service.payOrder(orderCode, isInstallments)
        return ResponseEntity.ok(mapOf("message" to "Order paid!"))
    }
}

