package it.marketplace.microservices.database.entity

import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Entity representing a user in the marketplace system.
 * Contains user details such as name, lastname, email, address, role, status, and relevant dates.
 */
@Entity
@Table(name = "USERS")
open class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    val id: Long? = null,

    @Column(name = "NAME", nullable = false)
    var name: String,

    @Column(name = "LASTNAME", nullable = false)
    var lastname: String,

    @Column(name = "EMAIL", unique = true, nullable = false)
    var email: String,

    @Column(name = "RESIDENCE_ADDRESS")
    var residenceAddress: String? = null,

    @Column(name = "RESIDENCE_CITY")
    var residenceCity: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    var role: RoleEnum,

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    var status: StatusUserEnum,

    @DateTimeFormat
    @Column(name = "TMS_SUBSCRIPTION_DATE", nullable = false)
    var tmsSubscriptionDate: LocalDateTime,

    @DateTimeFormat
    @Column(name = "TMS_UPDATE", nullable = false)
    var tmsUpdate: LocalDateTime

) : Serializable
