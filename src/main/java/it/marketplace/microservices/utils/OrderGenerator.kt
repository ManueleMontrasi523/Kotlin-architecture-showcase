package it.marketplace.microservices.utils

/**
 * Utility object for generating unique order codes in the marketplace system.
 */
object OrderGenerator {
    /**
     * Generates a unique order code in the format ORDERXXXXXX, where XXXXXX is a random 6-digit number.
     *
     * @return the generated order code
     */
    @JvmStatic
    fun generateOrderCode(): String {
        val random6Digits = (100000..999999).random()
        return "ORDER$random6Digits"
    }
}

