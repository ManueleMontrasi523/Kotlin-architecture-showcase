package it.marketplace.microservices.config.interceptor

import it.marketplace.microservices.common.enums.ErrorCode
import it.marketplace.microservices.config.exception.ServiceException
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ExceptionCustomHandlerTest {
    private val handler = ExceptionCustomHandler()

    @Test
    fun shouldHandleServiceExceptionAndReturnApiError() {
        // Arrange
        val ex = ServiceException(ErrorCode.PRODUCT_NOT_FOUND, "Product not found!")
        // Act
        val response: ResponseEntity<ApiError> = handler.handleGenericException(ex)
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        val error = response.body
        assertNotNull(error)
        assertEquals(HttpStatus.BAD_REQUEST.value(), error.status)
        assertEquals(ErrorCode.PRODUCT_NOT_FOUND, error.code)
        assertEquals("Product not found!", error.message)
    }
}

