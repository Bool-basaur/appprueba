package com.example.appprueba.adapters.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * =============================================================================
 * @Class: PriceResponseDTO
 * @Layer: Inbound Adapter (REST) — DTO
 * @Description: Represents the DTO returned to clients via the REST API.
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponseDTO {

    private Long productId;
    private Long brandId;
    private Long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
}
