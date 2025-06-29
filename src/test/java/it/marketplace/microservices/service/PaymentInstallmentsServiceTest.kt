package it.marketplace.microservices.service

import PaymentInstallmentsDto
import it.marketplace.microservices.database.repository.PaymentInstallmentsRepository
import it.marketplace.microservices.service.impl.PaymentInstallmentsServiceImpl
import it.marketplace.microservices.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PaymentInstallmentsServiceTest : BaseTest() {

    @Mock
    private val repository: PaymentInstallmentsRepository = mock()

    @InjectMocks
    private val service: PaymentInstallmentsServiceImpl = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun shouldFindAllByCode_WhenInstallmentsExist_ThenArrangeActAssert() {
        // Arrange
        val orderCode = "ORD123"
        val entity = mockPaymentInstallmentsEntity()
        Mockito.`when`(repository.findByOrderCode(orderCode)).thenReturn(listOf(entity))
        // Act
        val result: List<PaymentInstallmentsDto?> = service.findAllByCode(orderCode)
        // Assert
        assertNotNull(result)
        assertEquals(1, result.size)
        Mockito.verify(repository).findByOrderCode(orderCode)
    }
}

