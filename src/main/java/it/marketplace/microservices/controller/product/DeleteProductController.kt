package it.marketplace.microservices.controller.product

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
@Tag(name = "Product API", description = "Product management")
class DeleteProductController(
    private val service: ProductService
) {
    /**
     * Deletes a product by its code.
     * @param code the product code to delete
     * @return a response entity with a confirmation message
     * @throws ServiceException if the product cannot be deleted
     */
    @DeleteMapping("/delete")
    fun delete(@RequestParam("productCode") code: String): ResponseEntity<Map<String, String>> {
        service.deleteByCode(code)
        return ResponseEntity.ok(mapOf("message" to "Product deleted!"))
    }
}

