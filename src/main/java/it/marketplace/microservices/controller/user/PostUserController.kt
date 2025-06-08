package it.marketplace.microservices.controller.user

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.resource.UserResource
import it.marketplace.microservices.common.resource.toDto
import it.marketplace.microservices.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@Tag(name = "User API", description = "User management")
class PostUserController(
    private val service: UserService
) {
    @PostMapping("/add")
    fun save(@RequestBody userResource: UserResource, bindingResult: BindingResult): ResponseEntity<Any> {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.allErrors)
        }
        service.save(userResource.toDto())
        return ResponseEntity.ok(mapOf("message" to "User added!"))
    }

    @PostMapping("/add-all")
    fun saveAll(@RequestBody resources: List<UserResource>): ResponseEntity<Map<String, String>> {
        service.saveAll(resources.map { it.toDto() })
        return ResponseEntity.ok(mapOf("message" to "Users added!"))
    }
}

