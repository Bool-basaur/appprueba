package com.example.appprueba.application.port.in;

import com.example.appprueba.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceService {

    public Price getPriceByDateAndProductAndBrand(LocalDateTime date, Long productId, Long brandId);
}
