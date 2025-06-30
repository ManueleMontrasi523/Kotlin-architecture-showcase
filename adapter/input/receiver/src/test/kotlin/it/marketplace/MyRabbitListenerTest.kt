package it.marketplace

class MyRabbitListenerTest : BaseTest() {

    @Mock
    private val service: TransactionService = mock()

    @InjectMocks
    private val listener: MyRabbitListener = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(Exception::class)
    fun shouldCallStartProcessing_WhenListenerNewOrder_ThenArrangeActAssert() {
        // Arrange
        val orderCode = "ORD123"
        // Act
        listener.listenerNewOrder(orderCode)
        // Assert
        Mockito.verify(service).startProcessing(orderCode)
    }

    @Test
    @Throws(Exception::class)
    fun shouldCallStartPendingPayment_WhenListenerPendingPayment_ThenArrangeActAssert() {
        // Arrange
        val message = mapOf("orderCode" to "ORD123")
        // Act
        listener.listenerPendingPayment(message)
        // Assert
        Mockito.verify(service).startPendingPayment(message)
    }
}