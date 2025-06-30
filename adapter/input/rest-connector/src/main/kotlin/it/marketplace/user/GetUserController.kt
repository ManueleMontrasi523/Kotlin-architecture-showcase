package it.marketplace.controller.user

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.common.dto.toResource
import it.marketplace.common.enums.StatusUserEnum
import it.marketplace.common.resource.UserResource
import it.marketplace.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@Tag(name = "User API", description = "User management")
class GetUserController(
    private val service: UserService
) {
    @GetMapping("/get-by-email")
    fun find(@RequestParam("email") email: String): ResponseEntity<UserResource> {
        return ResponseEntity.ok(service.findByEmail(email)?.toResource())
    }

    @GetMapping("/get-all")
    fun findAll(@RequestParam("status") status: StatusUserEnum?): ResponseEntity<List<UserResource>> {
        return ResponseEntity.ok(service.findByStatus(status).map { it.toResource() })
    }
}

