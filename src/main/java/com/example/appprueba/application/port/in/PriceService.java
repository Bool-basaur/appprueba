package com.example.appprueba.application.port.in;

import com.example.appprueba.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

    Optional<Price> getPriceByDateAndProductAndBrand(Long productId, Long brandId, LocalDateTime date);
}
