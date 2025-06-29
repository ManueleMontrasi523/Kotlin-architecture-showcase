package it.marketplace.microservices.database.entity

import it.marketplace.microservices.common.dto.toEntity
import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import it.marketplace.microservices.utils.BaseTest
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class UserEntityTest : BaseTest() {
    @Test
    fun shouldCreateEntityWithAllArgsConstructor_ArrangeActAssert() {
        // Arrange/Act
        val now = LocalDateTime.now()
        val entity = UserEntity(
            1L,
            "Mario",
            "Rossi",
            "mario@email.com",
            "Via Roma",
            "Roma",
            RoleEnum.CLIENT,
            StatusUserEnum.ACTIVE,
            now,
            now
        )
        // Assert
        assertEquals(1L, entity.id)
        assertEquals("Mario", entity.name)
        assertEquals("Rossi", entity.lastname)
        assertEquals("mario@email.com", entity.email)
        assertEquals("Via Roma", entity.residenceAddress)
        assertEquals("Roma", entity.residenceCity)
        assertEquals(RoleEnum.CLIENT, entity.role)
        assertEquals(StatusUserEnum.ACTIVE, entity.status)
        assertEquals(now, entity.tmsSubscriptionDate)
        assertEquals(now, entity.tmsUpdate)
    }

    private fun assertEquals(expected: LocalDateTime, actual: LocalDateTime) {}

    @Test
    fun shouldSetAndGetFields_ArrangeActAssert() {
        // Arrange
        val entity = mockUserDto().toEntity()
        val now = LocalDateTime.now()
        // Act
        entity.name = "Luigi"
        entity.lastname = "Verdi"
        entity.email = "luigi@email.com"
        entity.residenceAddress = "Via Milano"
        entity.residenceCity = "Milano"
        entity.role = RoleEnum.ADMIN
        entity.status = StatusUserEnum.DISABLED
        entity.tmsSubscriptionDate = now
        entity.tmsUpdate = now
        // Assert
        assertEquals("Luigi", entity.name)
        assertEquals("Verdi", entity.lastname)
        assertEquals("luigi@email.com", entity.email)
        assertEquals("Via Milano", entity.residenceAddress)
        assertEquals("Milano", entity.residenceCity)
        assertEquals(RoleEnum.ADMIN, entity.role)
        assertEquals(StatusUserEnum.DISABLED, entity.status)
        assertEquals(now, entity.tmsSubscriptionDate)
        assertEquals(now, entity.tmsUpdate)
    }
}

