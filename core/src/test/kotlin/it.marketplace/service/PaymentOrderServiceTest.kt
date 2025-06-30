package it.marketplace.service

import it.marketplace.common.dto.PaymentOrderDto
import it.marketplace.common.enums.StatusOrderEnum
import it.marketplace.database.repository.PaymentInstallmentsRepository
import it.marketplace.database.repository.PaymentOrderRepository
import it.marketplace.service.impl.PaymentOrderServiceImpl
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.Mockito.mock
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PaymentOrderServiceTest : BaseTest() {
    @Mock
    private val repository: PaymentOrderRepository = mock()

    @Mock
    private val orderService: OrderService = mock()

    @Mock
    private val paymentInstallmentsRepository: PaymentInstallmentsRepository = mock()

    @InjectMocks
    private val service: PaymentOrderServiceImpl = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun shouldFindOrderByEmail_WhenOrdersExist_ThenArrangeActAssert() {
        // Arrange
        val email = "test@email.com"
        val orderDto = mockOrderDto()
        orderDto.orderCode = "ORD123"
        Mockito.`when`(orderService.findByUserMail(email)).thenReturn(listOf(orderDto))
        val entity = mockPaymentOrderEntity()
        Mockito.`when`(repository.findByOrderCodeIn(listOf("ORD123")))
            .thenReturn(listOf(entity))
        // Act
        val result: List<PaymentOrderDto?> = service.findOrderByEmail(email)
        // Assert
        assertNotNull(result)
        assertEquals(1, result.size)
        Mockito.verify(orderService).findByUserMail(email)
        Mockito.verify(repository).findByOrderCodeIn(listOf("ORD123"))
    }

    @Test
    fun shouldFindAll_WhenPaymentOrdersExist_ThenArrangeActAssert() {
        // Arrange
        val entity = mockPaymentOrderEntity()
        Mockito.`when`(repository.findAll()).thenReturn(listOf(entity))
        // Act
        val result: List<PaymentOrderDto?> = service.findAll()
        // Assert
        assertNotNull(result)
        assertEquals(1, result.size)
        Mockito.verify(repository).findAll()
    }

    @Test
    fun shouldPayOrder_WhenNotInstallments_ThenArrangeActAssert() {
        // Arrange
        val orderCode = "ORD123"
        val entity = mockPaymentOrderEntity()
        entity.status = StatusOrderEnum.PENDING_PAYMENT
        entity.debit = 120.0
        Mockito.`when`(repository.findByOrderCodeIgnoreCase(orderCode)).thenReturn(entity)
        // Act
        service.payOrder(orderCode, false)
        // Assert
        assertEquals(StatusOrderEnum.PAID, entity.status)
        Mockito.verify(orderService).payOrder(orderCode)
        Mockito.verify(repository).save(entity)
    }

    @Test
    fun shouldPayOrder_WhenInstallments_ThenArrangeActAssert() {
        // Arrange
        val orderCode = "ORD124"
        val entity = mockPaymentOrderEntity()
        entity.status = StatusOrderEnum.PENDING_PAYMENT
        entity.debit = 120.0
        Mockito.`when`(repository.findByOrderCodeIgnoreCase(orderCode)).thenReturn(entity)
        // Act
        service.payOrder(orderCode, true)
        // Assert
        assertEquals(StatusOrderEnum.RATEIZED, entity.status)
        Mockito.verify(paymentInstallmentsRepository).saveAll(ArgumentMatchers.anyList())
        Mockito.verify(repository).save(entity)
    }
}
