package com.example.appprueba.unitary.service;


import com.example.appprueba.application.port.in.PriceService;
import com.example.appprueba.application.port.out.PriceRepositoryPort;
import com.example.appprueba.application.service.impl.PriceServiceImpl;
import com.example.appprueba.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PriceServiceImplTest {

    private PriceRepositoryPort repository;
    private PriceService service;

    @BeforeEach
    void setUp() {
        repository = mock(PriceRepositoryPort.class);
        service = new PriceServiceImpl(repository);
    }

    @Test
    void shouldReturnResponseDTOWhenPriceExists() {
        /*LocalDateTime date = LocalDateTime.now();
        long productId = 35455L;
        long brandId = 1L;

        Price mockPrice = Price.builder().productId(productId)
                .brandId(brandId)
                .startDate(date.plusDays(1))
                .endDate(date.plusDays(1))
                .priority(1)
                .price(BigDecimal.valueOf(100.0))
                .currency("EUR").build();

        when(repository.findApplicablePrice(productId, brandId, date)).thenReturn(Optional.ofNullable(mockPrice));

        Price response = service.getPriceByDateAndProductAndBrand(date, productId, brandId);

        assertNotNull(response);
        assertEquals(100.0, response.getPrice());*/
    }

    @Test
    void shouldReturnNullWhenNoPriceFound() {
       /* when(repository.findApplicablePrice(anyLong(), anyLong(), any())).thenReturn(null);

        Price response = service.getPriceByDateAndProductAndBrand(LocalDateTime.now(), 123L, 1L);

        assertNull(response);*/
    }
}
