package it.marketplace.microservices.common.resource;

import io.swagger.v3.oas.annotations.media.Schema
import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import java.time.LocalDateTime

/**
 * Resource class representing a user in the marketplace system for API responses.
 * Contains user details such as name, email, address, status, role, and subscription date.
 */
data class UserResource(

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var id: Long,

    var name: String,
    var lastname: String,
    var email: String,
    var residenceAddress: String,
    var residenceCity: String,

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var status: StatusUserEnum,
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var role: RoleEnum,
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var tmsSubscriptionDate: LocalDateTime

)