package com.example.appprueba.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * =============================================================================
 * @Class: Price
 * @Layer: Domain Model
 * @Description: Represents the domain model for price information. It has the
 *              details of the price for a specific product and brand within a
 *              given date range.
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
}
