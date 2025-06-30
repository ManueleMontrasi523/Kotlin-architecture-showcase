package it.marketplace.service

import it.marketplace.common.dto.toEntity
import it.marketplace.database.repository.OrderRepository
import it.marketplace.database.repository.UserRepository
import it.marketplace.service.impl.OrderServiceImpl
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertNotNull

class OrderServiceTest : BaseTest() {

    @Mock
    private val orderRepository: OrderRepository = mock()

    @Mock
    private val userRepository: UserRepository = mock()

    @InjectMocks
    private val orderService: OrderServiceImpl = mock()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun shouldFindByCode_WhenOrderExists_ThenArrangeActAssert() {
        // Arrange
        val code = "ORD123"
        val entity = mockOrderDto().toEntity()
        Mockito.`when`(orderRepository.findByOrderCodeIgnoreCase(code)).thenReturn(entity)
        // Act
        val result: it.marketplace.common.dto.OrderDto? = orderService.findByCode(code)
        // Assert
        assertNotNull(result)
        Mockito.verify(orderRepository).findByOrderCodeIgnoreCase(code)
    }
}

