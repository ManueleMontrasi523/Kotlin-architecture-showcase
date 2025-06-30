package it.marketplace.controller.user

import it.marketplace.common.enums.StatusUserEnum
import it.marketplace.config.exception.ServiceException
import it.marketplace.service.UserService
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals

class StatusUserControllerTest : BaseTest() {

    @Mock
    private val service: UserService = mock()

    @InjectMocks
    private val controller: StatusUserController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldUpdateUserStatus_WhenValidEmailAndStatus_ThenArrangeActAssert() {
        // Arrange
        val email = "test@email.com"
        val status = StatusUserEnum.ACTIVE
        Mockito.doNothing().`when`(service).statusByEmail(email, status)

        // Act
        val response: ResponseEntity<Map<String, String>> = controller.status(email, status)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("User updated!", response.getBody()!!["message"])
        Mockito.verify(service).statusByEmail(email, status)
    }
}