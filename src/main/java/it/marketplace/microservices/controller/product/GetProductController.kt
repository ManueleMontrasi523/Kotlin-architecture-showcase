package it.marketplace.microservices.controller.product

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.dto.ProductDto
import it.marketplace.microservices.common.resource.ProductResource
import it.marketplace.microservices.config.mapper.ProductMapper
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
        return ResponseEntity.ok(ProductMapper.toResource(service.findByCode(code)))
    }

    @GetMapping("/get-all")
    fun findAll(): ResponseEntity<List<ProductResource>> {
        val dtos: List<ProductDto> = service.findAll()
        val resources = dtos.map { ProductMapper.toResource(it) }
        return ResponseEntity.ok(resources)
    }
}

