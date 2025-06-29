package it.marketplace.microservices.service;

import it.marketplace.microservices.database.repository.PaymentInstallmentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentInstallmentsServiceTest {

    @Mock
    private PaymentInstallmentsRepository repository;

    @InjectMocks
    private PaymentInstallmentsServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAllByCode_WhenInstallmentsExist_ThenArrangeActAssert() {
        // Arrange
        String orderCode = "ORD123";
        PaymentInstallmentsEntity entity = new PaymentInstallmentsEntity();
        when(repository.findByOrderCode(orderCode)).thenReturn(List.of(entity));
        // Act
        List<PaymentInstallmentsDto> result = service.findAllByCode(orderCode);
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository).findByOrderCode(orderCode);
    }
}

