package com.example.appprueba.unitary.service;


import com.example.appprueba.application.port.out.PriceRepositoryPort;
import com.example.appprueba.application.service.impl.PriceServiceImpl;
import com.example.appprueba.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class PriceServiceImplTest {

    private PriceRepositoryPort repositoryPort;
    private PriceServiceImpl service;

    @BeforeEach
    void setUp() {
        repositoryPort = mock(PriceRepositoryPort.class);
        service = new PriceServiceImpl(repositoryPort);
    }

    @Test
    void shouldReturnPriceWhenFound() {
        // given
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        Price mockPrice = Price.builder()
                .brandId(1L)
                .productId(35455L)
                .priceList(1L)
                .price(BigDecimal.valueOf(35.50))
                .build();

        // when
        when(repositoryPort.findApplicablePrice(productId, brandId, date))
                .thenReturn(Optional.of(mockPrice));

        Optional<Price> result = service.getPriceByDateAndProductAndBrand(productId, brandId, date);
        // then
        assertThat(result).isPresent();
        assertThat(result.get().getPrice()).isEqualByComparingTo("35.50");
    }

    @Test
    void shouldReturnEmptyWhenNotFound() {
        // given
        when(repositoryPort.findApplicablePrice(any(), any(), any()))
                .thenReturn(Optional.empty());

        //when
        Optional<Price> result = service.getPriceByDateAndProductAndBrand(1L, 1L, LocalDateTime.now());

        //then
        assertThat(result).isEmpty();
    }
}
