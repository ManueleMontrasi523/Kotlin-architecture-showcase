package it.marketplace.microservices.controller.product;

import it.marketplace.microservices.common.dto.ProductDto;
import it.marketplace.microservices.common.resource.ProductResource;
import it.marketplace.microservices.config.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class PostProductControllerTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private PostProductController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddProduct_WhenValidResource_ThenArrangeActAssert() throws ServiceException {
        // Arrange
        ProductResource resource = new ProductResource();
        ProductDto dto = ProductMapper.toDto(resource);
        doNothing().when(service).save(dto);

        // Act
        ResponseEntity<Map<String, String>> response = controller.save(resource);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product added!", response.getBody().get("message"));
        verify(service).save(dto);
    }

    @Test
    void shouldAddAllProducts_WhenValidResources_ThenArrangeActAssert() throws ServiceException {
        // Arrange
        ProductResource resource = new ProductResource();
        List<ProductResource> resources = List.of(resource);
        List<ProductDto> dtos = resources.stream().map(ProductMapper::toDto).toList();
        doNothing().when(service).saveAll(dtos);

        // Act
        ResponseEntity<Map<String, String>> response = controller.saveAll(resources);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Products added!", response.getBody().get("message"));
        verify(service).saveAll(dtos);
    }
}

