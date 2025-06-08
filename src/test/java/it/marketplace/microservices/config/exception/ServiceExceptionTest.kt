package it.marketplace.microservices.config.exception

import it.marketplace.microservices.common.enums.ErrorCode
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ServiceExceptionTest {
    @Test
    fun shouldCreateServiceExceptionWithErrorCodeAndMessage() {
        // Arrange
        val code = ErrorCode.PRODUCT_NOT_FOUND
        val message = "Product not found!"
        // Act
        val ex = ServiceException(code, message)
        // Assert
        assertEquals(code, ex.errorCode)
        assertEquals(message, ex.message)
    }

    @Test
    fun shouldCreateServiceExceptionWithNoArgs() {
        // Act
        val ex = ServiceException(
            ErrorCode.GENERIC_ERROR,
            "An unexpected error occurred."
        )
        // Assert
        assertNull(ex.errorCode)
        assertNull(ex.message)
    }

    @Test
    fun shouldContainAllErrorCodes() {
        // Act & Assert
        assertNotNull(ErrorCode.GENERIC_ERROR)
        assertNotNull(ErrorCode.DATA_ALREADY_PRESENT)
        assertNotNull(ErrorCode.EMAIL_NOT_FOUND)
        assertNotNull(ErrorCode.PRODUCT_NOT_FOUND)
        assertNotNull(ErrorCode.PRODUCT_SUPPLY_EXCEED)
        assertNotNull(ErrorCode.ORDER_NOT_FOUND)
        assertNotNull(ErrorCode.ORDER_EXIST_FOR_USER_FOUND)
        assertNotNull(ErrorCode.PAYMENT_RATE_EXCEED)
    }
}

