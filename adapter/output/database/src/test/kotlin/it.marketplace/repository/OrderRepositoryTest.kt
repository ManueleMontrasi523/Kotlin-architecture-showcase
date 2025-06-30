package it.marketplace.database.repository

import it.marketplace.common.dto.toEntity
import it.marketplace.common.enums.RoleEnum
import it.marketplace.common.enums.StatusOrderEnum
import it.marketplace.common.enums.StatusUserEnum
import it.marketplace.database.entity.OrderEntity
import it.marketplace.utils.BaseTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@DataJpaTest
open class OrderRepositoryTest : BaseTest() {

    @Autowired
    private val orderRepository: OrderRepository = mock()

    @Autowired
    private val userRepository: UserRepository = mock()

    @Test
    fun shouldFindByOrderCodeIgnoreCase_ArrangeActAssert() {
        // Arrange
        val user = mockUserDto().toEntity()
        user.email = "test@email.com"
        user.name = "user"
        user.lastname = "3"
        user.role = RoleEnum.CLIENT
        user.status = StatusUserEnum.ACTIVE
        user.tmsUpdate = LocalDateTime.now()
        user.tmsSubscriptionDate = LocalDateTime.now()
        userRepository.save(user)
        val order = mockOrderDto().toEntity()
        order.orderCode = "ORD123"
        order.user = user
        order.status = StatusOrderEnum.CREATED
        order.tmsUpdate = LocalDateTime.now()
        order.orderDate = LocalDateTime.now()
        orderRepository.save(order)
        // Act
        val found: OrderEntity? = orderRepository.findByOrderCodeIgnoreCase("ord123")
        // Assert
        assertNotNull(found)
        assertEquals("ORD123", found.orderCode)
    }

    @Test
    fun shouldFindOrderByUserMailAndStatus_ArrangeActAssert() {
        // Arrange
        val user = mockUserDto().toEntity()
        user.email = "user2@email.com"
        user.name = "user"
        user.lastname = "3"
        user.role = RoleEnum.CLIENT
        user.status = StatusUserEnum.ACTIVE
        user.tmsUpdate = LocalDateTime.now()
        user.tmsSubscriptionDate = LocalDateTime.now()
        userRepository.save(user)
        val order = mockOrderDto().toEntity()
        order.orderCode = "ORD456"
        order.user = user
        order.status = StatusOrderEnum.CREATED
        order.tmsUpdate = LocalDateTime.now()
        order.orderDate = LocalDateTime.now()
        orderRepository.save(order)
        // Act
        val found: OrderEntity? =
            orderRepository.findOrderByUserMailAndStatus("user2@email.com", StatusOrderEnum.CREATED)
        // Assert
        assertNotNull(found)
        assertEquals("ORD456", found.orderCode)
    }

    @Test
    fun shouldFindOrderByUserMail_ArrangeActAssert() {
        // Arrange
        val user = mockUserDto().toEntity()
        user.email = "user"
        user.name = "3"
        user.lastname = "user3@email.com"
        user.role = RoleEnum.CLIENT
        user.status = StatusUserEnum.ACTIVE
        user.tmsUpdate = LocalDateTime.now()
        user.tmsSubscriptionDate = LocalDateTime.now()
        userRepository.save(user)
        val order = mockOrderDto().toEntity()
        order.orderCode = "ORD789"
        order.user = user
        order.status = StatusOrderEnum.CREATED
        order.tmsUpdate = LocalDateTime.now()
        order.orderDate = LocalDateTime.now()
        orderRepository.save(order)
        // Act
        val found: List<OrderEntity?> = orderRepository.findOrderByUserMail("user3@email.com")
        // Assert
        assertFalse(found.isEmpty())
        assertEquals("ORD789", found[0]?.orderCode)
    }

    @Test
    fun shouldFindByStatus_ArrangeActAssert() {
        // Arrange
        val user = mockUserDto().toEntity()
        user.email = "user"
        user.name = "3"
        user.lastname = "user4@email.com"
        user.role = RoleEnum.CLIENT
        user.status = StatusUserEnum.ACTIVE
        user.tmsUpdate = LocalDateTime.now()
        user.tmsSubscriptionDate = LocalDateTime.now()
        userRepository.save(user)
        val order = mockOrderDto().toEntity()
        order.orderCode = "ORD000"
        order.user = user
        order.status = StatusOrderEnum.CREATED
        order.tmsUpdate = LocalDateTime.now()
        order.orderDate = LocalDateTime.now()
        orderRepository.save(order)
        // Act
        val found: List<OrderEntity?> = orderRepository.findByStatus(StatusOrderEnum.CREATED)
        // Assert
        assertFalse(found.isEmpty())
        assertTrue(found.stream().anyMatch { o -> o?.orderCode.equals("ORD000") })
    }
}

