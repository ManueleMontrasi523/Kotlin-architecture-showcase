package it.marketplace.microservices.controller.user

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@Tag(name = "User API", description = "User management")
class DeleteUserController(
    private val service: UserService
) {
    @DeleteMapping("/delete")
    fun delete(@RequestParam("email") email: String): ResponseEntity<Map<String, String>> {
        service.deleteByEmail(email)
        return ResponseEntity.ok(mapOf("message" to "User deleted!"))
    }
}

