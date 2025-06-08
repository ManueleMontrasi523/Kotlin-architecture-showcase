package it.marketplace.microservices.common.resource

import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class UserResourceTest {
    @Test
    fun shouldCreateUserResourceWithAllFields() {
        // Arrange
        val tmsSubscriptionDate = LocalDateTime.now()
        // Act
        val resource = UserResource(
            1L,
            "John",
            "Doe",
            "john.doe@email.com",
            "Main Street",
            "London",
            StatusUserEnum.ACTIVE,
            RoleEnum.CLIENT,
            tmsSubscriptionDate,
            LocalDateTime.now()
        )
        // Assert
        assertEquals(1L, resource.id)
        assertEquals("John", resource.name)
        assertEquals("Doe", resource.lastname)
        assertEquals("john.doe@email.com", resource.email)
        assertEquals("Main Street", resource.residenceAddress)
        assertEquals("London", resource.residenceCity)
        assertEquals(StatusUserEnum.ACTIVE, resource.status)
        assertEquals(RoleEnum.CLIENT, resource.role)
        assertEquals(tmsSubscriptionDate, resource.tmsSubscriptionDate)
    }

    @Test
    fun shouldSetAndGetIdAndTmsSubscriptionDate() {
        // Arrange
        val resource = UserResource(
            id = 0L,
            name = "Jane",
            lastname = "Doe",
            email = "",
            residenceAddress = "",
            residenceCity = "",
            status = StatusUserEnum.ACTIVE,
            role = RoleEnum.ADMIN,
            tmsSubscriptionDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now()
        )
        val tmsSubscriptionDate = LocalDateTime.now()
        // Act
        resource.id = 88L
        resource.tmsSubscriptionDate = tmsSubscriptionDate
        // Assert
        assertEquals(88L, resource.id)
        assertEquals(tmsSubscriptionDate, resource.tmsSubscriptionDate)
    }
}

