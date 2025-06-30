package it.marketplace.database.repository

import it.marketplace.common.enums.StatusOrderEnum
import it.marketplace.database.entity.PaymentInstallmentsEntity
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@DataJpaTest
open class PaymentInstallmentsRepositoryTest : BaseTest() {

    @Autowired
    private val repository: PaymentInstallmentsRepository = mock()

    @Test
    fun shouldFindByOrderCode_ArrangeActAssert() {
        // Arrange
        val now = LocalDateTime.now()
        val entity = mockPaymentInstallmentsEntity()
        entity.orderCode = "ORD1"
        entity.status = StatusOrderEnum.CREATED
        entity.debit = 0.59
        entity.reference = "ORDER"
        entity.tmsUpdate = LocalDateTime.now()
        repository.save(entity)
        // Act
        val found: List<PaymentInstallmentsEntity?> = repository.findByOrderCode("ORD1")
        // Assert
        assertFalse(found.isEmpty())
        assertEquals("ORD1", found[0]?.orderCode)
    }

    @Test
    fun shouldFindByOrderCodeAndStatus_ArrangeActAssert() {
        // Arrange
        val now = LocalDateTime.now()
        val entity = mockPaymentInstallmentsEntity()
        entity.orderCode = "ORD2"
        entity.status = StatusOrderEnum.PAID
        entity.debit = 0.59
        entity.reference = "ORDER"
        entity.tmsUpdate = LocalDateTime.now()
        repository.save(entity)
        // Act
        val found: List<PaymentInstallmentsEntity?> =
            repository.findByOrderCodeAndStatus("ORD2", StatusOrderEnum.PAID)
        // Assert
        assertFalse(found.isEmpty())
        assertEquals(StatusOrderEnum.PAID, found[0]?.status)
    }
}

