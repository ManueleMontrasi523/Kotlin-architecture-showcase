package it.marketplace.microservices.database.repository

import it.marketplace.microservices.common.dto.toEntity
import it.marketplace.microservices.common.enums.RoleEnum
import it.marketplace.microservices.common.enums.StatusUserEnum
import it.marketplace.microservices.database.entity.UserEntity
import it.marketplace.microservices.utils.BaseTest
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@DataJpaTest
open class UserRepositoryTest : BaseTest() {

    @Autowired
    private val repository: UserRepository = mock()

    @Autowired
    private val entityManager: EntityManager = mock()

    @Test
    fun shouldFindByEmailIgnoreCase_ArrangeActAssert() {
        // Arrange
        val entity = mockUserDto().toEntity()
        entity.name = ("user")
        entity.lastname = ("3")
        entity.email = ("test@email.com")
        entity.role = (RoleEnum.CLIENT)
        entity.tmsSubscriptionDate = (LocalDateTime.now())
        entity.tmsUpdate = (LocalDateTime.now())
        entity.status = (StatusUserEnum.ACTIVE)
        repository.save(entity)
        // Act
        val found: UserEntity? = repository.findByEmailIgnoreCase("TEST@email.com")
        // Assert
        assertNotNull(found)
        assertEquals("test@email.com", found.email)
    }

    @Test
    fun shouldFindAllByEmailIgnoreCaseIn_ArrangeActAssert() {
        // Arrange
        val entity = mockUserDto().toEntity()
        entity.name = ("user")
        entity.lastname = ("3")
        entity.email = ("user2@email.com")
        entity.role = (RoleEnum.CLIENT)
        entity.tmsSubscriptionDate = (LocalDateTime.now())
        entity.tmsUpdate = (LocalDateTime.now())
        entity.status = (StatusUserEnum.ACTIVE)
        repository.save(entity)
        // Act
        val found: List<UserEntity?> = repository.findAllByEmailIgnoreCaseIn(listOf("USER2@email.com"))
        // Assert
        assertFalse(found.isEmpty())
        assertEquals("user2@email.com", found[0]?.email)
    }

    @Test
    fun shouldFindAllByStatus_ArrangeActAssert() {
        // Arrange
        val entity = mockUserDto().toEntity()
        entity.name = ("user")
        entity.lastname = ("3")
        entity.email = ("user3@email.com")
        entity.role = (RoleEnum.CLIENT)
        entity.tmsSubscriptionDate = (LocalDateTime.now())
        entity.tmsUpdate = (LocalDateTime.now())
        entity.status = (StatusUserEnum.ACTIVE)
        repository.save(entity)
        // Act
        val found: List<UserEntity?> = repository.findAllByStatus(StatusUserEnum.ACTIVE)
        // Assert
        assertFalse(found.isEmpty())
        assertTrue(found.stream().anyMatch { u -> u?.email.equals("user3@email.com") })
    }

    @Test
    fun shouldUpdateStatusRelationshipsByEmail_ArrangeActAssert() {
        // Arrange
        val entity = mockUserDto().toEntity()
        entity.name = ("user")
        entity.lastname = ("3")
        entity.email = ("user4@email.com")
        entity.role = (RoleEnum.CLIENT)
        entity.tmsSubscriptionDate = (LocalDateTime.now())
        entity.tmsUpdate = (LocalDateTime.now())
        entity.status = (StatusUserEnum.ACTIVE)
        repository.save(entity)
        // Act
        repository.statusRelationshipsByEmail("user4@email.com", StatusUserEnum.DISABLED)
        entityManager.flush()
        entityManager.clear()
        val updated: UserEntity? = repository.findByEmailIgnoreCase("user4@email.com")
        // Assert
        assertNotNull(updated)
        assertEquals(StatusUserEnum.DISABLED, updated.status)
    }
}

