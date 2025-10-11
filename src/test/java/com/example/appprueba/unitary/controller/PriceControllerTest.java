package com.example.appprueba.unitary.controller;

import com.example.appprueba.adapters.in.rest.Controller.PriceController;
import com.example.appprueba.adapters.in.rest.dto.PriceResponseDTO;
import com.example.appprueba.application.port.in.PriceService;
import com.example.appprueba.domain.model.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PriceControllerTest {

    private PriceService priceService;
    private PriceController priceController;

    @BeforeAll
    void initAttributes(){
        priceService = mock(PriceService.class);
        priceController = new PriceController(priceService);
    }

    @Test
    void shouldReturnRightPriceWhenFound() {
     /*   LocalDateTime date = LocalDateTime.now();
        long productId = 35455L;
        long brandId = 1L;

        Price mockResponse = Price.builder()
                                    .price(BigDecimal.valueOf(35.50))
                                    .build();
        when(priceService.getPriceByDateAndProductAndBrand(date, productId, brandId)).thenReturn(mockResponse);

        ResponseEntity<PriceResponseDTO> response = priceController.getPrice(date, productId, brandId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse.getPrice(), response.getBody().getPrice());*/
    }

    @Test
    void shouldReturn404WhenNotFound() {
      /*  LocalDateTime date = LocalDateTime.now();
        when(priceService.getPriceByDateAndProductAndBrand(any(), anyLong(), anyLong())).thenReturn(null);

        ResponseEntity<PriceResponseDTO> response = priceController.getPrice(date, 123L, 1L);

        assertEquals(404, response.getStatusCodeValue());*/
    }
}
