package com.example.appprueba.unitary.repository;
import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import com.example.appprueba.adapters.out.jpa.mapper.PriceMapper;
import com.example.appprueba.adapters.out.jpa.repository.PriceJpaRepository;
import com.example.appprueba.adapters.out.jpa.repository.PriceRepositoryAdapter;
import com.example.appprueba.domain.model.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PriceRepositoryAdapterTest {

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceRepositoryAdapter adapter;

    private PriceEntity priceEntity;
    private Price price;

    @Test
    void shouldReturnPriceWhenApplicable() {
        /*Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");

        when(priceJpaRepository.findTopByProductAndBrandAndDateOrderByPriorityDesc(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());
        when(priceMapper.toDomain(priceEntity)).thenReturn(price);

        Optional<Price> result = adapter.findApplicablePrice(productId, brandId, applicationDate);

        assertTrue(result.isPresent());
        assertEquals(price, result.get());
        verify(priceJpaRepository).findTopByProductAndBrandAndDateOrderByPriorityDesc(productId, brandId, applicationDate);*/
    }

    @Test
    void shouldReturnEmptyWhenNoPriceFound() {
       /* Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");

        when(priceJpaRepository.findTopByProductAndBrandAndDateOrderByPriorityDesc(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());

        Optional<Price> result = adapter.findApplicablePrice(productId, brandId, applicationDate);

        assertFalse(result.isPresent());*/
    }
}