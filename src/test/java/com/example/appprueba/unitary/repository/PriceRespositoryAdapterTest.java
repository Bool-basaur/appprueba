package com.example.appprueba.unitary.repository;
import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import com.example.appprueba.adapters.out.jpa.repository.PriceJpaRepository;
import com.example.appprueba.mapper.persistence.PricePersistenceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @Mock
    private PricePersistenceMapper pricePersistenceMapper;

    @Test
    void shouldMapEntityToDomain() {
        // given
        PriceEntity entity = PriceEntity.builder()
                .brandId(1L)
                .productId(35455L)
                .startDate(LocalDateTime.of(2020,6,14,15,0,0))
                .endDate(LocalDateTime.of(2020,6,14,18,30,0))
                .priceList(2L)
                .priority(1)
                .price(new BigDecimal("25.45"))
                .curr("EUR")
                .build();

        // when
        when(priceJpaRepository.findTopByProductAndBrandAndDateOrderByPriorityDesc(any(), any(), any()))
                .thenReturn(new ArrayList<>());


        List<PriceEntity> result = priceJpaRepository.findTopByProductAndBrandAndDateOrderByPriorityDesc(35455L, 1L,
                LocalDateTime.of(2020,6,14,16,0,0));

        // then
        assertTrue(result.isEmpty());
        verify(priceJpaRepository, times(1))
                .findTopByProductAndBrandAndDateOrderByPriorityDesc(any(), any(), any());
    }

}