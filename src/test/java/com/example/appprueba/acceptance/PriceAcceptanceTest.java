package com.example.appprueba.acceptance;

import com.example.appprueba.adapters.in.rest.dto.PriceResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceAcceptanceTest {

    private static final long BRAND_ID = 1L;
    private static final long PRODUCT_ID = 35455L;

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    int port;
    @Test
    @DisplayName("Should return the applicable price for product 35455 on 2020-06-14 at 10:00")
    void acceptanceScenarioFor20200614_10h() {
        String url = buildRequestUrl("2020-06-14T10:00:00");
        ResponseEntity<PriceResponseDTO> resp = restTemplate.getForEntity(url, PriceResponseDTO.class);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(1L, resp.getBody().getPriceList());
    }



    private String buildRequestUrl(String date) {
        return String.format(
                "http://localhost:%d/api/v1/brands/%d/products/%d/prices?date=%s",
                port, BRAND_ID, PRODUCT_ID, date
        );
    }

}