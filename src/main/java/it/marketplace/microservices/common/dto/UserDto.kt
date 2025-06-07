package it.marketplace.microservices.common.dto

import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
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
    var residenceAddress: String,
    var residenceCity: String,
    var status: StatusUserEnum,
    var role: RoleEnum,
    var tmsSubscriptionDate: LocalDateTime

)