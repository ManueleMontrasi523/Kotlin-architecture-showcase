package it.marketplace.microservices.common.resource

import io.swagger.v3.oas.annotations.media.Schema
import it.marketplace.microservices.common.dto.UserDto
import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import it.marketplace.microservices.database.entity.UserEntity
import java.time.LocalDateTime

/**
 * Resource class representing a user in the marketplace system for API responses.
 * Contains user details such as name, email, address, status, role, and subscription date.
 */
data class UserResource(

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var id: Long,

    var name: String?,
    var lastname: String?,
    var email: String?,
    var residenceAddress: String?,
    var residenceCity: String?,

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var status: StatusUserEnum,
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var role: RoleEnum,
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var tmsSubscriptionDate: LocalDateTime,
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var tmsUpdate: LocalDateTime

)

fun UserResource.toDto() = UserDto(
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

fun UserResource.toEntity() = UserEntity(
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