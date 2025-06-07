package it.marketplace.microservices.config.validation

import it.marketplace.microservices.common.resource.UserResource
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

/**
 * Validator for UserResource objects in the marketplace system.
 * Validates required fields such as name, lastname, and email format.
 */
@Component
class UserValidator : Validator {
    companion object {
        private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$"
        private val EMAIL_PATTERN = Regex(EMAIL_REGEX)
    }

    override fun supports(clazz: Class<*>): Boolean {
        return UserResource::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        val resource = target as UserResource

        if (StringUtils.isEmpty(resource.name))
            errors.reject("ERROR_VALIDATION", "Name is mandatory!")
        if (StringUtils.isEmpty(resource.lastname))
            errors.reject("ERROR_VALIDATION", "lastname is mandatory!")
        if (StringUtils.isEmpty(resource.email) || !EMAIL_PATTERN.matches(resource.email))
            errors.reject("ERROR_VALIDATION", "Email missing or with format invalid!")
    }
}

