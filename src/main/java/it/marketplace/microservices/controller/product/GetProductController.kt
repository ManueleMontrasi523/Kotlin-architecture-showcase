package it.marketplace.microservices.controller.product

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.dto.toResource
import it.marketplace.microservices.common.resource.ProductResource
import it.marketplace.microservices.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
@Tag(name = "Product API", description = "Product management")
class GetProductController(
    private val service: ProductService
) {
    @GetMapping("/get-by-code")
    fun find(@RequestParam("productCode") code: String): ResponseEntity<ProductResource> {
        return ResponseEntity.ok(service.findByCode(code).toResource())
    }

    @GetMapping("/get-all")
    fun findAll(): ResponseEntity<List<ProductResource>> {
        return ResponseEntity.ok(service.findAll().map { it.toResource() })
    }
}

