package it.marketplace.microservices.config.interceptor

import it.marketplace.microservices.common.enums.ErrorCode
import org.junit.Test
import kotlin.test.assertEquals

class ApiErrorTest {

    @Test
    fun shouldCreateApiErrorWithAllFields() {
        // Arrange
        val status = 400
        val code = ErrorCode.GENERIC_ERROR
        val message = "An error occurred"
        // Act
        val error = ApiError(status, code, message)
        // Assert
        assertEquals(status, error.status)
        assertEquals(code, error.code)
        assertEquals(message, error.message)
    }

    @Test
    fun shouldSetAndGetFields() {
        // Arrange
        val error = ApiError(
            status = 0,
            code = ErrorCode.GENERIC_ERROR,
            message = ""
        )
        val status = 404
        val code = ErrorCode.PRODUCT_NOT_FOUND
        val message = "Product not found"

        // Assert
        assertEquals(status, error.status)
        assertEquals(code, error.code)
        assertEquals(message, error.message)
    }
}

