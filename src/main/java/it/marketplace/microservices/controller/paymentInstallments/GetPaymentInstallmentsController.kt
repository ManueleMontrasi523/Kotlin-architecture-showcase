package it.marketplace.microservices.controller.paymentInstallments

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.resource.PaymentInstallmentsResource
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.PaymentInstallmentsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import toResource

@RestController
@RequestMapping("/payment-order")
@Tag(name = "Payment Order API", description = "Payment Order management")
class GetPaymentInstallmentsController(
    private val service: PaymentInstallmentsService
) {
    /**
     * Retrieves all payment installments for a given order code.
     * @param orderCode the order code to retrieve installments for
     * @return a response entity containing a list of payment installment resources
     * @throws ServiceException if the installments cannot be retrieved
     */
    @GetMapping("/get-by-code")
    fun find(@RequestParam("orderCode") orderCode: String): ResponseEntity<List<PaymentInstallmentsResource?>?> {
        return ResponseEntity.ok(service.findAllByCode(orderCode).map { it.toResource() })
    }
}

