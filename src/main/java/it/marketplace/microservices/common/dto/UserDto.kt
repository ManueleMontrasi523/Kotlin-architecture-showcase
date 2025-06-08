package it.marketplace.microservices.common.dto

import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import it.marketplace.microservices.common.resource.UserResource
import it.marketplace.microservices.database.entity.UserEntity
import java.time.LocalDateTime

/**
 * Data Transfer Object (DTO) representing a user in the marketplace system.
 * Contains user details such as name, email, address, status, role, and subscription date.
 */
data class UserDto(
    var id: Long,
    var name: String,
    var lastname: String,
    var email: String,
    var residenceAddress: String?,
    var residenceCity: String?,
    var status: StatusUserEnum,
    var role: RoleEnum,
    var tmsSubscriptionDate: LocalDateTime,
    var tmsUpdate: LocalDateTime
)

fun UserDto.toResource() = UserResource(
    id = this.id,
    name = this.name,
    lastname = this.lastname,
    email = this.email,
    residenceAddress = this.residenceAddress,
    residenceCity = this.residenceCity,
    status = this.status,
    role = this.role,
    tmsSubscriptionDate = this.tmsSubscriptionDate,
    tmsUpdate = this.tmsUpdate
)

fun UserDto.toEntity() = UserEntity(
    id = this.id,
    name = this.name,
    lastname = this.lastname,
    email = this.email,
    residenceAddress = this.residenceAddress,
    residenceCity = this.residenceCity,
    status = this.status,
    role = this.role,
    tmsSubscriptionDate = this.tmsSubscriptionDate,
    tmsUpdate = this.tmsUpdate
)