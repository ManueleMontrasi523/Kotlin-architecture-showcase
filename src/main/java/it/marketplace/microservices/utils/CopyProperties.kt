package it.marketplace.microservices.utils

import org.springframework.beans.BeanUtils
import org.springframework.beans.BeanWrapperImpl

/**
 * Utility class for copying non-null properties from a source object to a target object.
 * Uses Spring's BeanUtils and BeanWrapper to perform the copy operation.
 */
object CopyProperties {
    /**
     * Copies all non-null properties from the source object to the target object.
     *
     * @param src the source object
     * @param target the target object
     */
    @JvmStatic
    fun copyNonNullProperties(src: Any, target: Any) {
        val srcWrap = BeanWrapperImpl(src)
        val nullPropertyNames = srcWrap.propertyDescriptors
            .map { it.name }
            .filter { srcWrap.getPropertyValue(it) == null }
            .toTypedArray()
        BeanUtils.copyProperties(src, target, *nullPropertyNames)
    }
}

