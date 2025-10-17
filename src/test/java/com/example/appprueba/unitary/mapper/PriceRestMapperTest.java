package com.example.appprueba.unitary.mapper;

import com.example.appprueba.adapters.in.rest.dto.PriceResponseDTO;
import com.example.appprueba.adapters.in.rest.mapper.PriceRestMapper;
import com.example.appprueba.domain.model.Prices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PriceRestMapperTest {
    PriceRestMapper priceRestMapper;

    @BeforeEach
    void setUp() {
        priceRestMapper = Mappers.getMapper(PriceRestMapper.class);    }
    @Test
    @DisplayName("Check if DTO is successfully mapped")
    void shouldMapDTOToDomainCorrectly() {
        PriceResponseDTO priceResponseDTO = createPriceResponseDTO();
        Prices p = priceRestMapper.toDomain(priceResponseDTO);

        assertThat(p.getProductId()).isEqualTo(35455L);
        assertThat(p.getBrandId()).isEqualTo(1L);
        assertThat(p.getPriceList()).isEqualTo(2L);
        assertThat(p.getPrice()).isEqualByComparingTo("25.45");
    }

    @Test
    @DisplayName("Check if domain object is successfully mapped")
    void shouldMapDomainToDTOCorrectly() {
        Prices p = createDomainPrice();
        PriceResponseDTO priceResponseDTO = priceRestMapper.toDto(p);
        assertThat(priceResponseDTO.getPrice()).isEqualByComparingTo("25.45");
    }
    private Prices createDomainPrice() {
        return Prices.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(2L)
                .price(BigDecimal.valueOf(25.45))
                .build();
    }

    private PriceResponseDTO createPriceResponseDTO() {
        return PriceResponseDTO.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(2L)
                .price(BigDecimal.valueOf(25.45))
                .build();
    }
}
