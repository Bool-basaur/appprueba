package com.example.appprueba.unitary.mapper;

import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import com.example.appprueba.adapters.out.jpa.mapper.PricePersistenceMapper;
import com.example.appprueba.domain.model.Prices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PricePersistenceMapperTest {

    PricePersistenceMapper pricePersistenceMapper;

    @BeforeEach
    void setUp() {
        pricePersistenceMapper = Mappers.getMapper(PricePersistenceMapper.class);    }
    @Test
    @DisplayName("Check if entity is successfully mapped")
    void shouldMapEntityToDomainCorrectly() {
        PriceEntity e = PriceEntity.builder().curr("EUR").price(new BigDecimal("25.45")).priceList(2L).build();
        Prices p = pricePersistenceMapper.toDomain(e);
        assertThat(p.getCurrency()).isEqualTo("EUR");
        assertThat(p.getPrice()).isEqualByComparingTo("25.45");
    }
}
