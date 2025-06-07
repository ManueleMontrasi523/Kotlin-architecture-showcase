package it.marketplace.microservices.controller.product

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.resource.ProductResource
import it.marketplace.microservices.config.mapper.ProductMapper
import it.marketplace.microservices.config.validation.ProductValidator
import it.marketplace.microservices.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
@Tag(name = "Product API", description = "Product management")
class PutProductController(
    private val validator: ProductValidator,
    private val service: ProductService
) {
    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.addValidators(validator)
    }

    @PutMapping("/update")
    fun update(@Valid @RequestBody resource: ProductResource): ResponseEntity<Map<String, String>> {
        service.update(ProductMapper.toDto(resource))
        return ResponseEntity.ok(mapOf("message" to "Product updated!"))
    }
}

