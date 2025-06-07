package it.marketplace.microservices.common.enums;

/**
 * Enum representing product categories in the marketplace system.
 */
enum class CategoryEnum {
    FOOD,
    TECHNOLOGIES,
    HOME
}

/**
 * Enum representing user roles in the marketplace system.
 */
enum class RoleEnum {
    ADMIN,
    CLIENT
}

/**
 * Enum representing the possible statuses of an order in the marketplace system.
 */
enum class StatusOrderEnum {
    CANCELLED,
    CREATED,
    PROCESSING,
    PENDING_PAYMENT,
    RATEIZED,
    PAID,
    REJECTED,
    FAILED
}

/**
 * Enum representing the possible statuses of a user in the marketplace system.
 */
enum class StatusUserEnum {
    ACTIVE,
    DISABLED
}

/**
 * Enum representing possible error codes for service exceptions.
 */
enum class ErrorCode {
    GENERIC_ERROR,
    DATA_ALREADY_PRESENT,
    EMAIL_NOT_FOUND,
    PRODUCT_NOT_FOUND,
    PRODUCT_SUPPLY_EXCEED,
    ORDER_NOT_FOUND,
    ORDER_EXIST_FOR_USER_FOUND,
    PAYMENT_RATE_EXCEED
}
