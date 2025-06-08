package it.marketplace.microservices.common.dto

import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class UserDtoTest {
    @Test
    fun shouldCreateUserDtoWithAllFields() {
        // Arrange
        val now = LocalDateTime.now()
        // Act
        val dto = UserDto(
            1L,
            "Mario",
            "Rossi",
            "mario.rossi@email.com",
            "Via Roma",
            "Rome",
            StatusUserEnum.ACTIVE,
            RoleEnum.CLIENT,
            now,
            now
        )
        // Assert
        assertEquals("1", dto.id.toString())
        assertEquals("Mario", dto.name)
        assertEquals("Rossi", dto.lastname)
        assertEquals("mario.rossi@email.com", dto.email)
        assertEquals("Via Roma", dto.residenceAddress)
        assertEquals("Rome", dto.residenceCity)
        assertEquals(StatusUserEnum.ACTIVE, dto.status)
        assertEquals(RoleEnum.CLIENT, dto.role)
        assertEquals(now, dto.tmsSubscriptionDate)
    }

    @Test
    fun shouldSetAndGetEmail() {
        // Arrange
        val dto = UserDto(
            id = 1L,
            name = "Mario",
            lastname = "Rossi",
            email = "",
            residenceAddress = "Via Roma",
            residenceCity = "Rome",
            status = StatusUserEnum.ACTIVE,
            role = RoleEnum.CLIENT,
            tmsSubscriptionDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now()
        )
        // Act
        dto.email = "test@email.com"
        // Assert
        assertEquals("test@email.com", dto.email)
    }
}

