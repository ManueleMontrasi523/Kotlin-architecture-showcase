package it.marketplace.controller.user

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.common.resource.UserResource
import it.marketplace.common.resource.toDto
import it.marketplace.config.validation.UserValidator
import it.marketplace.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
@Tag(name = "User API", description = "User management")
class PutUserController(
    private val validator: UserValidator,
    private val service: UserService
) {
    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.addValidators(validator)
    }

    @PutMapping("/update")
    fun update(@Valid @RequestBody userResource: UserResource): ResponseEntity<Map<String, String>> {
        service.update(userResource.toDto())
        return ResponseEntity.ok(mapOf("message" to "User updated!"))
    }
}

