package com.example.appprueba.unitary.adapter;
import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import com.example.appprueba.adapters.out.jpa.repository.PriceJpaRepository;
import com.example.appprueba.adapters.out.jpa.mapper.PricePersistenceMapper;
import com.example.appprueba.adapters.out.jpa.repository.PriceRepositoryAdapter;
import com.example.appprueba.domain.model.Prices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @Mock
    private PricePersistenceMapper pricePersistenceMapper;

    @InjectMocks
    private PriceRepositoryAdapter adapter;

    @Test
    @DisplayName("Check that the mapped domain is returned as expected")
    void shouldReturnMappedDomainWhenEntityFound() {
        // given
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);

        PriceEntity entity = createPriceEntity();

        Prices domain = createDomainPrice();

        //when
        when(priceJpaRepository.findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                any(), any(), any(), any()))
                .thenReturn(Optional.of(entity));

        when(pricePersistenceMapper.toDomain(entity)).thenReturn(domain);


        Optional<Prices> result = adapter.findApplicablePrice(35455L, 1L, date);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getPrice()).isEqualByComparingTo("25.45");

        // verify
        verify(priceJpaRepository, times(1))
                .findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        35455L, 1L, date, date);
        verify(pricePersistenceMapper, times(1)).toDomain(entity);
    }

    @Test
    @DisplayName("Check that empty optional is returned when repository returns empty")
    void shouldReturnEmptyWhenRepositoryReturnsEmpty() {
        // given
        when(priceJpaRepository.findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                any(), any(), any(), any()))
                .thenReturn(Optional.empty());

        // when
        Optional<Prices> result = adapter.findApplicablePrice(999L, 999L, LocalDateTime.now());

        // then
        assertThat(result).isEmpty();

        // verify the repository was called
        verify(priceJpaRepository, times(1))
                .findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(any(), any(), any(), any());

        // verify that the mapper was NOT called
        verify(pricePersistenceMapper, never()).toDomain(any());
    }

    private PriceEntity createPriceEntity(){
        return PriceEntity.builder()
                .brandId(1L)
                .productId(35455L)
                .priceList(2L)
                .price(BigDecimal.valueOf(25.45))
                .build();
    }

    private Prices createDomainPrice(){
        return Prices.builder()
                .brandId(1L)
                .productId(35455L)
                .priceList(2L)
                .price(BigDecimal.valueOf(25.45))
                .build();
    }

}