package it.marketplace.microservices.controller.product

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.resource.ProductResource
import it.marketplace.microservices.common.resource.toDto
import it.marketplace.microservices.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
@Tag(name = "Product API", description = "Product management")
class PostProductController(
    private val service: ProductService
) {
    @PostMapping("/add")
    fun save(@RequestBody resource: ProductResource): ResponseEntity<Map<String, String>> {
        service.save(resource.toDto())
        return ResponseEntity.ok(mapOf("message" to "Product added!"))
    }

    @PostMapping("/add-all")
    fun saveAll(@RequestBody resources: List<ProductResource>): ResponseEntity<Map<String, String>> {
        service.saveAll(resources.map { it.toDto() })
        return ResponseEntity.ok(mapOf("message" to "Products added!"))
    }
}

