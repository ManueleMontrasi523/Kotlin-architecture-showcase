package it.marketplace.microservices.controller.paymentInstallments

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.PaymentInstallmentsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment-order")
@Tag(name = "Payment Order API", description = "Payment Order management")
class PutPaymentInstallmentsController(
    private val service: PaymentInstallmentsService
) {
    /**
     * Pays a specific installment (rate) for an order.
     * @param orderCode the order code for which to pay the installment
     * @param number the installment number to pay
     * @return a response entity with a confirmation message
     * @throws ServiceException if the payment cannot be processed
     */
    @PutMapping("/pay-rate")
    fun pay(
        @RequestParam("orderCode") orderCode: String,
        @RequestParam("number") number: Int
    ): ResponseEntity<Map<String, String>> {
        service.payInstallments(orderCode, number)
        return ResponseEntity.ok(mapOf("message" to "Rate paid!"))
    }
}

