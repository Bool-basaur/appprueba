package com.example.appprueba.unitary.controller;

import com.example.appprueba.adapters.in.rest.controller.PriceController;
import com.example.appprueba.adapters.in.rest.dto.PriceResponseDTO;
import com.example.appprueba.adapters.in.rest.exception.GlobalExceptionHandler;
import com.example.appprueba.application.port.in.PriceService;
import com.example.appprueba.domain.model.Prices;
import com.example.appprueba.adapters.in.rest.mapper.PriceRestMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PriceController.class)
@Import(GlobalExceptionHandler.class)
class PriceControllerUnitTest {

    private static final String BASE_PATH = "/brands/{brandId}/products/{productId}/prices";
    private static final String BRAND_ID = "1";
    private static final String PRODUCT_ID = "35455";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PriceService priceService;

    @MockitoBean
    private PriceRestMapper priceRestMapper;

    @Test
    @DisplayName("Check that 200 response is returned after a successful request")
    void shouldReturn200WithValidPrice() throws Exception {
        //given
        Prices domainPrice = createDomainPrice();
        PriceResponseDTO dto = createPriceResponseDTO();

        //when
        when(priceService.getPriceByDateAndProductAndBrand(any(), any(), any()))
                .thenReturn(Optional.of(domainPrice));
        when(priceRestMapper.toDto(domainPrice)).thenReturn(dto);

        //then
        performGet("2020-06-14T10:00:00")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }
    @Test
    @DisplayName("Check that 400 response is returned after a request with invalid parameters is done")
    void shouldReturn400ForInvalidParameter() throws Exception {
        //given

        //when

        //then
        performGet("sdfgsdfg")
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("400 Bad Request when a not null parameter is missing")
    void shouldReturn400WhenMissingParameter() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get(BASE_PATH, BRAND_ID, PRODUCT_ID))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("400 Bad Request when brandId or productId is negative")
    void shouldReturn400WhenNegativeParameter() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get(BASE_PATH, BRAND_ID, -1)
                .param("date", "2020-06-14T10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation Failed"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("Check that 404 response is returned when the price is not found")
    void shouldReturn404WhenPriceNotFound() throws Exception {
        //given

        //when
        when(priceService.getPriceByDateAndProductAndBrand(any(), any(), any()))
                .thenReturn(Optional.empty());

        //then
        performGet("2020-06-14T10:00:00")
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Price Not Found"))
                .andExpect(jsonPath("$.message").value("No price found for the given parameters"));
    }

    @Test
    @DisplayName("Check that 500 response is returned when the mapper fails")
    void shouldReturn500WhenMapperFails() throws Exception {
        //given
        Prices domainPrice = createDomainPrice();

        // when
        when(priceService.getPriceByDateAndProductAndBrand(any(), any(), any()))
                .thenReturn(Optional.of(domainPrice));


        when(priceRestMapper.toDto(any())).thenThrow(new RuntimeException("map error"));

        // then
        performGet("2020-06-14T10:00:00")
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Internal Server Error"))
                .andExpect(jsonPath("$.message").value("map error"));
    }

    private ResultActions performGet(String date) throws Exception {
        return mockMvc.perform(get(BASE_PATH, BRAND_ID, PRODUCT_ID)
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON));
    }

    private Prices createDomainPrice() {
        return Prices.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(1L)
                .price(BigDecimal.valueOf(35.50))
                .build();
    }

    private PriceResponseDTO createPriceResponseDTO() {
        return PriceResponseDTO.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(1L)
                .price(BigDecimal.valueOf(35.50))
                .build();
    }

}
