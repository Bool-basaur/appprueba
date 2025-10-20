package com.example.appprueba.integration.repository;

import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import com.example.appprueba.adapters.out.jpa.repository.PriceJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PriceJpaRepositoryTest {
    private static final long BRAND_ID = 1L;
    private static final long PRODUCT_ID = 35455L;

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    @Test
    @DisplayName("Find correct price for 2020-06-14T10:00:00")
    void testPriceAt20200614_10h() {
        LocalDateTime queryDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        Optional<PriceEntity> price = priceJpaRepository
                .findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        PRODUCT_ID, BRAND_ID, queryDate, queryDate);

        assertThat(price).isPresent();
        assertThat(price.get().getPriority()).isZero();
        assertThat(price.get().getPrice()).isEqualByComparingTo(new BigDecimal("35.50"));
    }

    @Test
    @DisplayName("Find correct price for 2020-06-14T16:00:00")
    void testPriceAt20200614_16h() {
        LocalDateTime queryDate = LocalDateTime.of(2020, 6, 14, 16, 0);

        Optional<PriceEntity> price = priceJpaRepository
                .findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        PRODUCT_ID, BRAND_ID, queryDate, queryDate);

        assertThat(price).isPresent();
        assertThat(price.get().getPriority()).isEqualTo(1);
        assertThat(price.get().getPrice()).isEqualByComparingTo(new BigDecimal("25.45"));
    }

    @Test
    @DisplayName("Find correct price for 2020-06-15T10:00:00")
    void testPriceAt20200615_10h() {
        LocalDateTime queryDate = LocalDateTime.of(2020, 6, 15, 10, 0);

        Optional<PriceEntity> price = priceJpaRepository
                .findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        PRODUCT_ID, BRAND_ID, queryDate, queryDate);

        assertThat(price).isPresent();
        assertThat(price.get().getPriority()).isEqualTo(1);
        assertThat(price.get().getPrice()).isEqualByComparingTo(new BigDecimal("30.50"));
    }

    @Test
    @DisplayName("No price found outside date ranges")
    void testNoPriceOutsideRanges() {
        LocalDateTime queryDate = LocalDateTime.of(2021, 1, 1, 0, 0);

        Optional<PriceEntity> price = priceJpaRepository
                .findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        PRODUCT_ID, BRAND_ID, queryDate, queryDate);

        assertThat(price).isEmpty();
    }
}
