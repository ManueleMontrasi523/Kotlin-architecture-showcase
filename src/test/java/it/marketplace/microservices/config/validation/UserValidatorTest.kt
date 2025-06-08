package it.marketplace.microservices.config.validation

import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import it.marketplace.microservices.common.resource.UserResource
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UserValidatorTest {
    private lateinit var validator: UserValidator

    @BeforeEach
    fun setUp() {
        validator = UserValidator()
    }

    @Test
    fun shouldSupportUserResourceClass() {
        // Act & Assert
        assertTrue(validator.supports(UserResource::class.java))
        assertFalse(validator.supports(String::class.java))
    }

    @Test
    fun shouldPassValidationForValidUser() {
        // Arrange
        val user = UserResource(
            name = "John",
            lastname = "Doe",
            email = "John@gmail.com",
            id = 1L,
            residenceAddress = "Via Roma 1",
            residenceCity = "Rome",
            status = StatusUserEnum.ACTIVE,
            role = RoleEnum.CLIENT,
            tmsSubscriptionDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now(),
        )
        val errors: Errors = BeanPropertyBindingResult(user, "user")
        // Act
        validator.validate(user, errors)
        // Assert
        assertFalse(errors.hasErrors())
    }

    @Test
    fun shouldRejectIfNameIsMissing() {
        // Arrange
        val user = UserResource(
            name = null,
            lastname = "Doe",
            email = "John@gmail.com",
            id = 1L,
            residenceAddress = "Via Roma 1",
            residenceCity = "Rome",
            status = StatusUserEnum.ACTIVE,
            role = RoleEnum.CLIENT,
            tmsSubscriptionDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now(),
        )
        val errors: Errors = BeanPropertyBindingResult(user, "user")
        // Act
        validator.validate(user, errors)
        // Assert
        assertTrue(errors.hasErrors())
        assertEquals("Name is mandatory!", errors.allErrors[0].defaultMessage)
    }

    @Test
    fun shouldRejectIfLastnameIsMissing() {
        // Arrange
        val user = UserResource(
            name = "John",
            lastname = null,
            email = "John@gmail.com",
            id = 1L,
            residenceAddress = "Via Roma 1",
            residenceCity = "Rome",
            status = StatusUserEnum.ACTIVE,
            role = RoleEnum.CLIENT,
            tmsSubscriptionDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now(),
        )
        val errors: Errors = BeanPropertyBindingResult(user, "user")
        // Act
        validator.validate(user, errors)
        // Assert
        assertTrue(errors.hasErrors())
        assertEquals("lastname is mandatory!", errors.allErrors[0].defaultMessage)
    }

    @Test
    fun shouldRejectIfEmailIsMissing() {
        // Arrange
        val user = UserResource(
            name = "John",
            lastname = "Doe",
            email = null,
            id = 1L,
            residenceAddress = "Via Roma 1",
            residenceCity = "Rome",
            status = StatusUserEnum.ACTIVE,
            role = RoleEnum.CLIENT,
            tmsSubscriptionDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now(),
        )
        val errors: Errors = BeanPropertyBindingResult(user, "user")
        // Act
        validator.validate(user, errors)
        // Assert
        assertTrue(errors.hasErrors())
        assertEquals("Email missing or with format invalid!", errors.allErrors[0].defaultMessage)
    }

    @Test
    fun shouldRejectIfEmailIsInvalid() {
        // Arrange
        val user = UserResource(
            name = "John",
            lastname = "Doe",
            email = "invalid-email",
            id = 1L,
            residenceAddress = "Via Roma 1",
            residenceCity = "Rome",
            status = StatusUserEnum.ACTIVE,
            role = RoleEnum.CLIENT,
            tmsSubscriptionDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now(),
        )
        val errors: Errors = BeanPropertyBindingResult(user, "user")
        // Act
        validator.validate(user, errors)
        // Assert
        assertTrue(errors.hasErrors())
        assertEquals("Email missing or with format invalid!", errors.allErrors[0].defaultMessage)
    }
}
