package it.marketplace.microservices.config.exception

import it.marketplace.microservices.common.enums.ErrorCode

/**
 * Custom exception for service layer errors in the marketplace system.
 * Includes an error code and message for detailed error handling.
 *
 * @property errorCode The error code associated with this exception.
 */
class ServiceException(
    val errorCode: ErrorCode,
    errorMessage: String?
) : RuntimeException(errorMessage)
