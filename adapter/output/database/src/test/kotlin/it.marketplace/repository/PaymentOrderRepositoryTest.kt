package it.marketplace.database.repository

import it.marketplace.common.enums.StatusOrderEnum
import it.marketplace.database.entity.PaymentOrderEntity
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.*
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@DataJpaTest
open class PaymentOrderRepositoryTest : BaseTest() {

    @Autowired
    private val repository: PaymentOrderRepository = mock()

    @Test
    fun shouldFindByOrderCodeIgnoreCase_ArrangeActAssert() {
        // Arrange
        val now = LocalDateTime.now()
        val entity = mockPaymentOrderEntity()
        entity.orderCode = ("ORD1")
        entity.status = (StatusOrderEnum.CREATED)
        entity.debit = (0.59)
        entity.orderDate = (LocalDateTime.now())
        entity.tmsUpdate = (LocalDateTime.now())
        repository!!.save<PaymentOrderEntity>(entity)
        // Act
        val found: PaymentOrderEntity = repository.findByOrderCodeIgnoreCase("ord1")
        // Assert
        assertNotNull(found)
        assertEquals("ORD1", found.orderCode)
    }

    @Test
    fun shouldFindByOrderCodeIn_ArrangeActAssert() {
        // Arrange
        val now = LocalDateTime.now()
        val entity = mockPaymentOrderEntity()
        entity.orderCode = ("ORD2")
        entity.status = (StatusOrderEnum.PAID)
        entity.debit = (0.59)
        entity.orderDate = (LocalDateTime.now())
        entity.tmsUpdate = (LocalDateTime.now())
        repository!!.save<PaymentOrderEntity>(entity)
        // Act
        val found: List<PaymentOrderEntity?> = repository.findByOrderCodeIn(listOf("ORD2"))
        // Assert
        assertFalse(found.isEmpty())
        assertEquals("ORD2", found[0]?.orderCode)
    }

    @Test
    fun shouldFindByStatus_ArrangeActAssert() {
        // Arrange
        val now = LocalDateTime.now()
        val entity = mockPaymentOrderEntity()
        entity.orderCode = ("ORD3")
        entity.status = (StatusOrderEnum.CREATED)
        entity.debit = (0.59)
        entity.orderDate = (LocalDateTime.now())
        entity.tmsUpdate = (LocalDateTime.now())
        repository!!.save<PaymentOrderEntity>(entity)
        // Act
        val found: List<PaymentOrderEntity?> = repository.findByStatus(StatusOrderEnum.CREATED)
        // Assert
        assertFalse(found.isEmpty())
        assertTrue(found.stream().anyMatch { e -> e?.orderCode.equals("ORD3") })
    }

    @Test
    fun shouldFindByOrderCodeAndStatus_ArrangeActAssert() {
        // Arrange
        val now = LocalDateTime.now()
        val entity = mockPaymentOrderEntity()
        entity.orderCode = ("ORD4")
        entity.status = (StatusOrderEnum.PAID)
        entity.debit = (0.59)
        entity.orderDate = (LocalDateTime.now())
        entity.tmsUpdate = (LocalDateTime.now())
        repository!!.save<PaymentOrderEntity>(entity)
        // Act
        val found: PaymentOrderEntity = repository.findByOrderCodeAndStatus("ORD4", StatusOrderEnum.CREATED)
        // Assert
        assertNotNull(found)
        assertEquals("ORD4", found.orderCode)
        assertEquals(StatusOrderEnum.PAID, found.status)
    }
}

