package it.marketplace.microservices.config.interceptor

import it.marketplace.microservices.config.exception.ServiceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Global exception handler for the marketplace system.
 * Handles ServiceException and returns a structured API error response.
 */
@ControllerAdvice
class ExceptionCustomHandler {

    /**
     * Handles ServiceException and returns an ApiError response with HTTP 400 status.
     *
     * @param ex the ServiceException thrown by the service layer
     * @return ResponseEntity containing the ApiError details
     */
    @ExceptionHandler(ServiceException::class)
    fun handleGenericException(ex: ServiceException): ResponseEntity<ApiError> {
        val status = HttpStatus.BAD_REQUEST
        val error = ApiError(
            status.value(),
            ex.errorCode,
            ex.message
        )
        return ResponseEntity.status(status).body(error)
    }
}

