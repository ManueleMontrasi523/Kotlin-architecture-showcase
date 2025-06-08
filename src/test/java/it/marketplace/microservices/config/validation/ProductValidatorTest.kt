package it.marketplace.microservices.config.validation

import it.marketplace.microservices.common.enums.CategoryEnum
import it.marketplace.microservices.common.resource.ProductResource
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ProductValidatorTest {
    private lateinit var validator: ProductValidator

    @BeforeEach
    fun setUp() {
        validator = ProductValidator()
    }

    @Test
    fun shouldSupportProductResourceClass() {
        // Act & Assert
        assertTrue(validator.supports(ProductResource::class.java))
        assertFalse(validator.supports(String::class.java))
    }

    @Test
    fun shouldPassValidationForValidProduct() {
        // Arrange
        val product = ProductResource(
            name = "Laptop",
            productCode = "PROD123",
            category = CategoryEnum.TECHNOLOGIES,
            supply = BigDecimal.TEN,
            price = 999.99,
            creationDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now(),
            id = 1L,
            description = "This is a high-end laptop."
        )
        val errors: Errors = BeanPropertyBindingResult(product, "product")
        // Act
        validator.validate(product, errors)
        // Assert
        assertFalse(errors.hasErrors())
    }

    @Test
    fun shouldRejectIfNameIsMissing() {
        // Arrange
        val product = ProductResource(
            productCode = "PROD123",
            category = CategoryEnum.TECHNOLOGIES,
            supply = BigDecimal.TEN,
            price = 999.99,
            creationDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now(),
            id = 1L,
            description = "This is a high-end laptop.",
            name = null // Name is missing
        )
        val errors: Errors = BeanPropertyBindingResult(product, "product")
        // Act
        validator.validate(product, errors)
        // Assert
        assertTrue(errors.hasErrors())
        assertEquals("Name is mandatory!", errors.allErrors[0].defaultMessage)
    }

    @Test
    fun shouldRejectIfProductCodeIsMissing() {
        // Arrange
        val product = ProductResource(
            name = "Laptop",
            // No productCode set
            category = CategoryEnum.TECHNOLOGIES,
            supply = BigDecimal.TEN,
            price = 999.99,
            creationDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now(),
            id = 1L,
            productCode = null, // ProductCode is missing
            description = "This is a high-end laptop."
        )

        val errors: Errors = BeanPropertyBindingResult(product, "product")
        // Act
        validator.validate(product, errors)
        // Assert
        assertTrue(errors.hasErrors())
        assertEquals("ProductCode is mandatory!", errors.allErrors[0].defaultMessage)
    }

    @Test
    fun shouldRejectIfCategoryIsInvalid() {
        // Arrange
        val product = ProductResource(
            name = "Laptop",
            productCode = "PROD123",
            // Invalid category (null)
            category = null,
            supply = BigDecimal.TEN,
            price = 999.99,
            creationDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now(),
            id = 1L,
            description = "This is a high-end laptop."
        )
        val errors: Errors = BeanPropertyBindingResult(product, "product")
        // Act
        validator.validate(product, errors)
        // Assert
        assertTrue(errors.hasErrors())
        assertEquals("Category is mandatory!", errors.allErrors[0].defaultMessage)
    }

    @Test
    fun shouldRejectIfSupplyIsMissingOrZero() {
        // Arrange
        val product = ProductResource(
            name = "Laptop",
            productCode = "PROD123",
            category = CategoryEnum.TECHNOLOGIES,
            // Supply is zero
            supply = BigDecimal.ZERO,
            price = 999.99,
            creationDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now(),
            id = 1L,
            description = "This is a high-end laptop."
        )
        val errors: Errors = BeanPropertyBindingResult(product, "product")
        // Act
        validator.validate(product, errors)
        // Assert
        assertTrue(errors.hasErrors())
        assertEquals("Supply is mandatory!", errors.allErrors[0].defaultMessage)
    }

    @Test
    fun shouldRejectIfPriceIsMissingOrNegative() {
        // Arrange
        val product = ProductResource(
            name = "Laptop",
            productCode = "PROD123",
            category = CategoryEnum.TECHNOLOGIES,
            supply = BigDecimal.TEN,
            // Price is negative
            price = -1.0,
            creationDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now(),
            id = 1L,
            description = "This is a high-end laptop."
        )
        val errors: Errors = BeanPropertyBindingResult(product, "product")
        // Act
        validator.validate(product, errors)
        // Assert
        assertTrue(errors.hasErrors())
        assertEquals("Price is mandatory!", errors.allErrors[0].defaultMessage)
    }
}

