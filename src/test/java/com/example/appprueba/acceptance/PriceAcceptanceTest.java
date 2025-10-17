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
    @Autowired
    TestRestTemplate restTemplate;
    @LocalServerPort
    int port;
    @Test
    @DisplayName("Should return the applicable price for product 35455 on 2020-06-14 at 10:00")
    void acceptanceScenarioFor20200614_10h() {
        String url = "http://localhost:" + port + "/api/v1/prices?date=2020-06-14T10:00:00&brandId=1&productId=35455";
        ResponseEntity<PriceResponseDTO> resp = restTemplate.getForEntity(url, PriceResponseDTO.class);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(1L, resp.getBody().getPriceList());
    }
}