package it.marketplace.microservices.config.validation

import it.marketplace.microservices.common.enums.CategoryEnum
import it.marketplace.microservices.common.resource.ProductResource
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import java.math.BigDecimal

/**
 * Validator for ProductResource objects in the marketplace system.
 * Validates required fields such as name, product code, category, supply, and price.
 */
@Component
class ProductValidator : Validator {
    private val categories = CategoryEnum.entries

    override fun supports(clazz: Class<*>): Boolean {
        return ProductResource::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        val resource = target as ProductResource

        if (StringUtils.isEmpty(resource.name))
            errors.reject("ERROR_VALIDATION", "Name is mandatory!")
        if (StringUtils.isEmpty(resource.productCode))
            errors.reject("ERROR_VALIDATION", "ProductCode is mandatory!")
        if (!categories.contains(resource.category))
            errors.reject("ERROR_VALIDATION", "Category is mandatory!")
        if (BigDecimal.ZERO == resource.supply)
            errors.reject("ERROR_VALIDATION", "Supply is mandatory!")
        if (resource.price.isNaN() || resource.price <= 0)
            errors.reject("ERROR_VALIDATION", "Price is mandatory!")
    }
}

