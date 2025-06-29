package it.marketplace.microservices.controller.user

import it.marketplace.microservices.common.dto.toResource
import it.marketplace.microservices.common.resource.toDto
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
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import kotlin.test.assertEquals

class PostUserControllerTest : BaseTest() {

    @Mock
    private val service: UserService = mock()

    @Mock
    private val bindingResult: BindingResult = mock()

    @InjectMocks
    private val controller: PostUserController = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldAddUser_WhenValidResource_ThenArrangeActAssert() {
        // Arrange
        val resource = mockUserDto().toResource()
        Mockito.`when`(bindingResult.hasErrors()).thenReturn(false)
        Mockito.doNothing().`when`(service).save(resource.toDto())

        // Act
        val response: ResponseEntity<*> = controller.save(resource, bindingResult)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("User added!", (response.getBody() as MutableMap<*, *>?)!!["message"])
        Mockito.verify(service).save(resource.toDto())
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldReturnBadRequest_WhenBindingResultHasErrors_ThenArrangeActAssert() {
        // Arrange
        val resource = mockUserDto().toResource()
        Mockito.`when`(bindingResult.hasErrors()).thenReturn(true)
        Mockito.`when`(bindingResult.allErrors).thenReturn(mutableListOf<ObjectError?>())

        // Act
        val response: ResponseEntity<*> = controller.save(resource, bindingResult)

        // Assert
        assertEquals(400, response.statusCode.value())
        Mockito.verify(bindingResult).allErrors
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldAddAllUsers_WhenValidResources_ThenArrangeActAssert() {
        // Arrange
        val resource = mockUserDto().toResource()
        val resources = listOf(resource)
        val dtos = listOf(mockUserDto())
        Mockito.doNothing().`when`(service).saveAll(dtos)

        // Act
        val response: ResponseEntity<*> = controller!!.saveAll(resources)

        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("Users added!", (response.getBody() as MutableMap<*, *>?)!!["message"])
        Mockito.verify(service).saveAll(dtos)
    }
}