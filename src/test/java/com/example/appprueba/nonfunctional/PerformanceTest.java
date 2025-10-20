package com.example.appprueba.nonfunctional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class PerformanceTest {
    private static final long BRAND_ID = 1;
    private static final long PRODUCT_ID = 35455;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Non functional test that checks that performance works as expected")
    void measureEachRequest() {
        IntStream.range(0, 100)
                .forEach(i -> {
                    try {
                        long start = System.nanoTime();
                        mockMvc.perform(get("/brands/" + BRAND_ID + "/products/" + PRODUCT_ID + "/prices")
                                        .param("date", "2020-06-14T10:00:00"))
                                .andExpect(status().isOk());
                        long end = System.nanoTime();
                        System.out.println("Request " + i + " took " + (end - start) / 1_000_000 + " ms");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
