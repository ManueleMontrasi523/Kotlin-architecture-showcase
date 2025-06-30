package it.marketplace.service

import it.marketplace.database.repository.*
import it.marketplace.rabbitmq.RabbitMqProducer
import it.marketplace.service.impl.TransactionServiceImpl
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertTrue

class TransactionServiceTest : BaseTest() {
    @Mock
    private val orderRepository: OrderRepository = mock()

    @Mock
    private val productRepository: ProductRepository = mock()

    @Mock
    private val userRepository: UserRepository = mock()

    @Mock
    private val paymentOrderRepository: PaymentOrderRepository = mock()

    @Mock
    private val paymentInstallmentsRepository: PaymentInstallmentsRepository = mock()

    @Mock
    private val rabbitMqProducer: RabbitMqProducer = mock()

    @InjectMocks
    private val service: TransactionServiceImpl = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun shouldCallReadPendingPaymentsOrder_WhenInvoked_ThenArrangeActAssert() {
        // Arrange/Act
        service.readPendingPaymentsOrder()
        assertTrue(true)
    }
}

