package it.marketplace.database.entity

import it.marketplace.common.dto.UserDto
import it.marketplace.common.enums.RoleEnum
import it.marketplace.common.enums.StatusUserEnum
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

/**
 * Entity representing a user in the marketplace system.
 * Contains user details such as name, lastname, email, address, role, status, and relevant dates.
 */
@Entity
@Table(name = "USERS")
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    val id: Long? = null,

    @Column(name = "NAME", nullable = false)
    var name: String = "",

    @Column(name = "LASTNAME", nullable = false)
    var lastname: String = "",

    @Column(name = "EMAIL", unique = true, nullable = false)
    var email: String = "",

    @Column(name = "RESIDENCE_ADDRESS")
    var residenceAddress: String? = null,

    @Column(name = "RESIDENCE_CITY")
    var residenceCity: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    var role: RoleEnum = RoleEnum.CLIENT,

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    var status: StatusUserEnum = StatusUserEnum.ACTIVE,

    @DateTimeFormat
    @Column(name = "TMS_SUBSCRIPTION_DATE", nullable = false)
    var tmsSubscriptionDate: LocalDateTime = LocalDateTime.now(),

    @DateTimeFormat
    @Column(name = "TMS_UPDATE", nullable = false)
    var tmsUpdate: LocalDateTime = LocalDateTime.now()

)

fun UserEntity.toDto() = UserDto(
    id = this.id,
    name = this.name,
    lastname = this.lastname,
    email = this.email,
    residenceAddress = this.residenceAddress,
    residenceCity = this.residenceCity,
    role = this.role,
    status = this.status,
    tmsSubscriptionDate = this.tmsSubscriptionDate,
    tmsUpdate = this.tmsUpdate
)
