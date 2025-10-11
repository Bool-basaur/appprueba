package com.example.appprueba.unitary.controller;

import com.example.appprueba.adapters.in.rest.Controller.PriceController;
import com.example.appprueba.adapters.in.rest.dto.PriceResponseDTO;
import com.example.appprueba.adapters.out.jpa.mapper.PriceMapper;
import com.example.appprueba.application.port.in.PriceService;
import com.example.appprueba.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PriceService priceService;

    @MockitoBean
    private PriceMapper priceMapper;

    @Test
    void shouldReturn200WithValidPrice() throws Exception {
        // given
        Price domainPrice = Price.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(1L)
                .price(BigDecimal.valueOf(35.50))
                .build();

        PriceResponseDTO dto = PriceResponseDTO.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(1L)
                .price(BigDecimal.valueOf(35.50))
                .build();

        // when
        Mockito.when(priceService.getPriceByDateAndProductAndBrand(any(), any(), any()))
                .thenReturn(Optional.of(domainPrice));

        Mockito.when(priceMapper.toDto(domainPrice)).thenReturn(dto);

        // then
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14T10:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void shouldReturn404WhenNoPriceFound() throws Exception {
        Mockito.when(priceService.getPriceByDateAndProductAndBrand(any(), any(), any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14T10:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andExpect(status().isNotFound());
    }
}
