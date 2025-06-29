package it.marketplace.microservices.controller.user

import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.service.UserService
import it.marketplace.microservices.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals

class DeleteUserControllerTest : BaseTest() {

    @Mock
    private val service: UserService = mock()

    @InjectMocks
    private val controller: DeleteUserController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldDeleteUser_WhenValidEmail_ThenArrangeActAssert() {
        // Arrange
        val email = "test@email.com"
        Mockito.doNothing().`when`(service).deleteByEmail(email)

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.delete(email)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("User deleted!", response.getBody()!!["message"])
        Mockito.verify(service).deleteByEmail(email)
    }
}