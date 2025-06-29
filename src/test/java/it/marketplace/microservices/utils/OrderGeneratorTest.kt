package it.marketplace.microservices.utils

import it.marketplace.microservices.utils.OrderGenerator.generateOrderCode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class OrderGeneratorTest {
    @Test
    fun shouldGenerateOrderCodeWithPrefixAndSixDigits_ArrangeActAssert() {
        // Arrange/Act
        val code = generateOrderCode()
        // Assert
        Assertions.assertNotNull(code)
        Assertions.assertTrue(code.startsWith("ORDER"))
        Assertions.assertEquals(11, code.length)
        val digits = code.substring(5)
        Assertions.assertTrue(digits.matches("\\d{6}".toRegex()))
    }
}

