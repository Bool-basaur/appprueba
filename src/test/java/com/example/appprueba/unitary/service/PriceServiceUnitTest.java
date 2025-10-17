package com.example.appprueba.unitary.service;


import com.example.appprueba.application.port.out.PriceRepositoryPort;
import com.example.appprueba.application.service.impl.PriceServiceImpl;
import com.example.appprueba.domain.model.Prices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class PriceServiceUnitTest {

    private PriceRepositoryPort repositoryPort;
    private PriceServiceImpl service;

    @BeforeEach
    void setUp() {
        repositoryPort = mock(PriceRepositoryPort.class);
        service = new PriceServiceImpl(repositoryPort);
    }

    @Test
    @DisplayName("Check if price is successfully returned when found")
    void shouldReturnPriceWhenFound() {
        // given
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        Prices mockPrice = createDomainPrice();

        // when
        when(repositoryPort.findApplicablePrice(productId, brandId, date))
                .thenReturn(Optional.of(mockPrice));

        Optional<Prices> result = service.getPriceByDateAndProductAndBrand(productId, brandId, date);
        // then
        assertThat(result).isPresent();
        assertThat(result.get().getPrice()).isEqualByComparingTo("35.50");
    }

    @Test
    @DisplayName("Check if empty is returned when price is not found")
    void shouldReturnEmptyWhenNotFound() {
        //given

        //when
        when(repositoryPort.findApplicablePrice(any(), any(), any()))
                .thenReturn(Optional.empty());


        Optional<Prices> result = service.getPriceByDateAndProductAndBrand(1L, 1L, LocalDateTime.now());

        //then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Check if the repository it's called once")
    void shouldDelegateToRepositoryJustOnce(){
        //given
        LocalDateTime date = LocalDateTime.now();

        //when
        when(repositoryPort.findApplicablePrice(any(), any(), any())).thenReturn(Optional.empty());
        service.getPriceByDateAndProductAndBrand(123123L, 123123L, date);

        //then
        verify(repositoryPort, times(1)).findApplicablePrice(123123L, 123123L, date);
    }

    @Test
    @DisplayName("Check if the exception is successfully propagated")
    void shouldPropagateRepositoryException() {
        //given
        LocalDateTime date = LocalDateTime.now();

        //when
        when(repositoryPort.findApplicablePrice(any(), any(), any()))
                .thenThrow(new RuntimeException("DB down"));

        //then
        assertThrows(RuntimeException.class, () ->
                service.getPriceByDateAndProductAndBrand(1231L, 1214214L, date));
    }

    private Prices createDomainPrice() {
        return Prices.builder()
                .brandId(1L)
                .productId(35455L)
                .priceList(1L)
                .price(BigDecimal.valueOf(35.50))
                .build();
    }
}
