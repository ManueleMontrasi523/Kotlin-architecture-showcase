package it.marketplace.microservices.controller.user

import io.swagger.v3.oas.annotations.tags.Tag
import it.marketplace.microservices.common.dto.UserDto
import it.marketplace.microservices.common.enums.StatusUserEnum
import it.marketplace.microservices.common.resource.UserResource
import it.marketplace.microservices.config.mapper.UserMapper
import it.marketplace.microservices.service.UserService
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
        return ResponseEntity.ok(UserMapper.toResource(service.findByEmail(email)))
    }

    @GetMapping("/get-all")
    fun findAll(@RequestParam("status") status: StatusUserEnum): ResponseEntity<List<UserResource>> {
        val dtos: List<UserDto> = service.findAll(status)
        val resources = dtos.map { UserMapper.toResource(it) }
        return ResponseEntity.ok(resources)
    }
}

