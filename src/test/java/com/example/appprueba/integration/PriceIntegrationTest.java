package com.example.appprueba.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PriceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Integration test for price at 2020-06-14T10:00:00")
    void shouldReturnPriceList1_at_14_10() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("date", "2020-06-14T10:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Integration test for price at 2020-06-14T16:00:00")
    void shouldReturnPriceList2_at_14_16() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("date", "2020-06-14T16:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    @DisplayName("Integration test for price at 2020-06-14T21:00:00")
    void shouldReturnPriceList1_at_14_21() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("date", "2020-06-14T21:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Integration test for price at 2020-06-15T10:00:00")
    void shouldReturnPriceList3_at_15_10() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("date", "2020-06-15T10:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    @DisplayName("Integration test for price at 2020-06-16T21:00:00")
    void shouldReturnPriceList4_at_16_21() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("date", "2020-06-16T21:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95));
    }

    @Test
    @DisplayName("Integration test for price with invalid date parameter")
    void shouldReturn400ForInvalidDateParameter() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("date", "invalid-date")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("Integration test checking the speed of the request")
    void shouldRespondWithin500ms() {
        assertTimeout(Duration.ofMillis(500), () -> {
            mockMvc.perform(get("/api/v1/prices")
                            .param("date","2020-06-14T10:00:00")
                            .param("brandId","1")
                            .param("productId","35455"))
                    .andExpect(status().isOk());
        });
    }


}