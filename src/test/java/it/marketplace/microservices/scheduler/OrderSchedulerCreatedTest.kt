package it.marketplace.microservices.scheduler

import it.marketplace.microservices.service.TransactionService
import it.marketplace.microservices.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class OrderSchedulerCreatedTest : BaseTest() {

    @Mock
    private val service: TransactionService = mock()

    @InjectMocks
    private val scheduler: OrderSchedulerCreated = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun shouldCallTransactionServiceMethods_WhenStartProcessOrder_ThenArrangeActAssert() {
        // Arrange/Act
        scheduler.startProcessOrder()
        // Assert
        Mockito.verify(service).readPendingPaymentsOrder()
        Mockito.verify(service).startAlignmentStatesOrder()
    }
}

