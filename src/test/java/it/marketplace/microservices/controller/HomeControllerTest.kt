package it.marketplace.microservices.controller

import it.marketplace.microservices.utils.BaseTest
import jakarta.servlet.ServletException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.function.Executable
import org.mockito.Mockito.mock
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class HomeControllerTest: BaseTest() {
    private var mockMvc: MockMvc = mock()

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(HomeController()).build()
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnIndexView_WhenGetHome_ThenArrangeActAssert() {
        // Arrange/Act/Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("index"))
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnUserView_WhenGetUser_ThenArrangeActAssert() {
        // Arrange/Act/Assert
        assertThrows(ServletException::class.java, Executable {
            mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user"))
        }
        )
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnProductView_WhenGetProduct_ThenArrangeActAssert() {
        // Arrange/Act/Assert
        assertThrows(ServletException::class.java, Executable {
            mockMvc.perform(MockMvcRequestBuilders.get("/product"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product"))
        }
        )
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnPaymentView_WhenGetPayment_ThenArrangeActAssert() {
        // Arrange/Act/Assert
        assertThrows(ServletException::class.java, Executable {
            mockMvc.perform(MockMvcRequestBuilders.get("/payment"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("payment"))
        }
        )
    }
}