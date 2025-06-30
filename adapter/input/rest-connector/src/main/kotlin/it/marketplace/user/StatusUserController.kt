package it.marketplace.controller.user

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.common.enums.StatusUserEnum
import it.marketplace.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@Tag(name = "User API", description = "User management")
class StatusUserController(
    private val service: UserService
) {
    @PutMapping("/status")
    fun status(@RequestParam("email") email: String, @RequestParam("status") status: StatusUserEnum): ResponseEntity<Map<String, String>> {
        service.statusByEmail(email, status)
        return ResponseEntity.ok(mapOf("message" to "User updated!"))
    }
}

