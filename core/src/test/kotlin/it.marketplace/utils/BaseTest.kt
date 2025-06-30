package it.marketplace.utils

import PaymentInstallmentsDto
import it.marketplace.common.dto.*
import it.marketplace.common.enums.CategoryEnum
import it.marketplace.common.enums.RoleEnum
import it.marketplace.common.enums.StatusOrderEnum
import it.marketplace.common.enums.StatusUserEnum
import it.marketplace.database.entity.PaymentInstallmentsEntity
import it.marketplace.database.entity.PaymentOrderEntity
import java.math.BigDecimal
import java.time.LocalDateTime

open class BaseTest {

    fun mockOrderDto(): OrderDto {
        return OrderDto(
            orderCode = "ORDER1",
            id = 1L,
            user = mockUserDto(),
            productOrder = listOf(mockProductOrderDto()),
            status = StatusOrderEnum.CREATED,
            rejectReason = null,
            orderDate = LocalDateTime.now(),
            tmsUpdate = LocalDateTime.now()
        )
    }

    fun mockUserDto(): UserDto = UserDto(
        id = 1L,
        name = "Mario",
        lastname = "Rossi",
        email = "mario.rossi@gmail.com",
        residenceAddress = "Via dei pini 15",
        residenceCity = "Milano",
        status = StatusUserEnum.ACTIVE,
        role = RoleEnum.CLIENT,
        tmsSubscriptionDate = LocalDateTime.now(),
        tmsUpdate = LocalDateTime.now()
    )


    fun mockProductOrderDto(): ProductOrderDto = ProductOrderDto(
        id = 1L,
        orderCode = "ORDER1",
        productCode = "PRODUCT1",
        quantity = BigDecimal.TEN,
        unitPrice = 1.0,
        total = 1.0,
        creationDate = LocalDateTime.now(),
        tmsUpdate = LocalDateTime.now()
    )


    fun mockPaymentInstallmentsDto(): PaymentInstallmentsDto =
        PaymentInstallmentsDto(
            id = 1L,
            reference = "REF01",
            orderCode = "ORDER1",
            status = StatusOrderEnum.CREATED,
            debit = 10.0,
            tmsUpdate = LocalDateTime.now()
        )


    fun mockPaymentOrderDto(): PaymentOrderDto = PaymentOrderDto(
        id = 1L,
        orderCode = "ORDER1",
        status = StatusOrderEnum.CREATED,
        debit = 10.0,
        orderDate = LocalDateTime.now(),
        tmsUpdate = LocalDateTime.now()
    )

    fun mockProductDto(): ProductDto = ProductDto(
        id = 1L,
        productCode = "PRODUC1",
        name = "CAROTE",
        description = "BONE",
        price = 1.9,
        supply = BigDecimal.TEN,
        category = CategoryEnum.FOOD,
        creationDate = LocalDateTime.now(),
        tmsUpdate = LocalDateTime.now()
    )

    fun mockPaymentInstallmentsEntity(): PaymentInstallmentsEntity =
        PaymentInstallmentsEntity(1L, "REF1", "ORD1", StatusOrderEnum.PAID, 100.0, LocalDateTime.now())

    fun mockPaymentOrderEntity(): PaymentOrderEntity =
        PaymentOrderEntity(1L, "ORD1", StatusOrderEnum.PAID, 100.0, LocalDateTime.now(), LocalDateTime.now())
}