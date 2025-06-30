package it.marketplace.service

import it.marketplace.common.dto.UserDto
import it.marketplace.common.dto.toEntity
import it.marketplace.database.repository.UserRepository
import it.marketplace.service.impl.UserServiceImpl
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertNotNull

class UserServiceTest : BaseTest() {
    @Mock
    private val repository: UserRepository = mock()

    @InjectMocks
    private val service: UserServiceImpl = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun shouldFindByEmail_WhenUserExists_ThenArrangeActAssert() {
        // Arrange
        val email = "test@email.com"
        val entity = mockUserDto().toEntity()
        Mockito.`when`(repository.findByEmailIgnoreCase(email)).thenReturn(entity)
        // Act
        val result: UserDto? = service.findByEmail(email)
        // Assert
        assertNotNull(result)
        Mockito.verify(repository).findByEmailIgnoreCase(email)
    }
}

