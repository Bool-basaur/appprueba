package com.example.appprueba.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class DataLoadIntegrationTest {

    private static final String SELECT_ALL_QUERY = "SELECT COUNT(*) FROM PRICES";

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    @DisplayName("Check if the database has loaded all the initial prices")
    void dataLoaded() {
        Integer count = jdbcTemplate.queryForObject(SELECT_ALL_QUERY, Integer.class);
        assertEquals(4, count);
    }
}
