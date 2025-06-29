package it.marketplace.microservices.controller.user

import it.marketplace.microservices.common.dto.toResource
import it.marketplace.microservices.common.enums.StatusUserEnum
import it.marketplace.microservices.common.resource.UserResource
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

class GetUserControllerTest : BaseTest() {

    @Mock
    private val service: UserService = mock()

    @InjectMocks
    private val controller: GetUserController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldReturnUser_WhenFindByEmail_ThenArrangeActAssert() {
        // Arrange
        val email = "test@email.com"
        val dto = mockUserDto()
        val expected = dto.toResource()
        Mockito.`when`(service.findByEmail(email)).thenReturn(dto)

        // Act
        val response: ResponseEntity<UserResource> = controller.find(email)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals(expected, response.getBody())
        Mockito.verify(service).findByEmail(email)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldReturnAllUsers_WhenFindAllByStatus_ThenArrangeActAssert() {
        // Arrange
        val status = StatusUserEnum.ACTIVE
        val dto = mockUserDto()
        val dtos = listOf(dto)
        Mockito.`when`(service.findByStatus(status)).thenReturn(dtos)
        val expected = listOf(dto.toResource())

        // Act
        val response: ResponseEntity<kotlin.collections.List<UserResource>> = controller.findAll(status)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals(expected, response.getBody())
        Mockito.verify(service).findByStatus(status)
    }
}