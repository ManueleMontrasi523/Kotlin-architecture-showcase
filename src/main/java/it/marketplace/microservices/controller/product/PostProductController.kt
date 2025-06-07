package it.marketplace.microservices.controller.product

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.resource.ProductResource
import it.marketplace.microservices.config.mapper.ProductMapper
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
        service.save(ProductMapper.toDto(resource))
        return ResponseEntity.ok(mapOf("message" to "Product added!"))
    }

    @PostMapping("/add-all")
    fun saveAll(@RequestBody resources: List<ProductResource>): ResponseEntity<Map<String, String>> {
        val dtos = resources.map { ProductMapper.toDto(it) }
        service.saveAll(dtos)
        return ResponseEntity.ok(mapOf("message" to "Products added!"))
    }
}

