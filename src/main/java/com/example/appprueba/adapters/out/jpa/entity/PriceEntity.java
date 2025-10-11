package com.example.appprueba.adapters.out.jpa.entity;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceEntity {

    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String curr;
}
