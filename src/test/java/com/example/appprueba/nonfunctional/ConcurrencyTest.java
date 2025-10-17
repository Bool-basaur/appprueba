package com.example.appprueba.nonfunctional;

import com.example.appprueba.application.port.in.PriceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConcurrencyTest {

    @Autowired
    private PriceService priceService;

    @Test
    @DisplayName("Non functional test that checks that concurrent access works")
    void concurrentAccessShouldWork() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    var result = priceService.getPriceByDateAndProductAndBrand(35455L, 1L, date);
                    assertThat(result).isNotNull();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
    }
}
