package com.example.appprueba.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PriceIntegrationTest {
    private static final String BASE_PATH = "/brands/{brandId}/products/{productId}/prices";
    private static final String BRAND_ID = "1";
    private static final String PRODUCT_ID = "35455";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Integration test for price at 2020-06-14T10:00:00")
    void shouldReturnPriceList1_at_14_10() throws Exception {
        performGet("2020-06-14T10:00:00")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Integration test for price at 2020-06-14T16:00:00")
    void shouldReturnPriceList2_at_14_16() throws Exception {
        performGet("2020-06-14T16:00:00")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    @DisplayName("Integration test for price at 2020-06-14T21:00:00")
    void shouldReturnPriceList1_at_14_21() throws Exception {
        performGet("2020-06-14T21:00:00")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Integration test for price at 2020-06-15T10:00:00")
    void shouldReturnPriceList3_at_15_10() throws Exception {
        performGet("2020-06-15T10:00:00")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    @DisplayName("Integration test for price at 2020-06-16T21:00:00")
    void shouldReturnPriceList4_at_16_21() throws Exception {
        performGet("2020-06-16T21:00:00")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95));
    }

    @Test
    @DisplayName("Integration test for price with invalid date parameter")
    void shouldReturn400ForInvalidDateParameter() throws Exception {
        performGet("invalid-date")
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("Integration test checking the speed of the request")
    void shouldRespondWithin500ms() {
        assertTimeout(Duration.ofMillis(500), () -> {
            performGet("2020-06-14T10:00:00")
                    .andExpect(status().isOk());
        });
    }



    private ResultActions performGet(String date) throws Exception {
        return mockMvc.perform(get(BASE_PATH, BRAND_ID, PRODUCT_ID)
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON));
    }


}