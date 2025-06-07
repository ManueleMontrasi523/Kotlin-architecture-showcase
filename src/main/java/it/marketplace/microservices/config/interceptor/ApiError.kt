package it.marketplace.microservices.config.interceptor

import it.marketplace.microservices.common.enums.ErrorCode

/**
 * Represents an API error response for the marketplace system.
 * Contains the HTTP status, error code, and error message to be returned to the client.
 *
 * @property status HTTP status code of the error response.
 * @property code Error code from [ErrorCode].
 * @property message Human-readable error message.
 */
data class ApiError(
    val status: Int,
    val code: ErrorCode?,
    val message: String?
)

