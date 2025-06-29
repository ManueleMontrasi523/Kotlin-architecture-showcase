package it.marketplace.microservices.controller.user

import it.marketplace.microservices.common.dto.toResource
import it.marketplace.microservices.common.resource.toDto
import it.marketplace.microservices.config.exception.ServiceException
import it.marketplace.microservices.config.validation.UserValidator
import it.marketplace.microservices.service.UserService
import it.marketplace.microservices.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import kotlin.test.assertEquals

class PutUserControllerTest : BaseTest() {

    @Mock
    private lateinit var service: UserService

    @Mock
    private lateinit var validator: UserValidator

    @Mock
    private lateinit var binder: WebDataBinder

    @InjectMocks
    private lateinit var controller: PutUserController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        controller.initBinder(binder)
    }

    @Test
    @Throws(ServiceException::class)
    fun shouldUpdateUser_WhenValidResource_ThenArrangeActAssert() {
        // Arrange
        val resource = mockUserDto().toResource()
        doNothing().`when`(service).update(resource.toDto())
        // Act
        val response: ResponseEntity<Map<String, String>> = controller.update(resource)
        // Assert
        assertEquals(200, response.statusCode.value())
        assertEquals("User updated!", response.body?.getValue("message"))
        verify(service).update(resource.toDto())
    }
}

