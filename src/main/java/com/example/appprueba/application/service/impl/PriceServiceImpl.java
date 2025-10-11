package com.example.appprueba.application.service.impl;

import com.example.appprueba.application.port.in.PriceService;
import com.example.appprueba.application.port.out.PriceRepositoryPort;
import com.example.appprueba.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Optional<Price> getPriceByDateAndProductAndBrand(Long productId, Long brandId, LocalDateTime date) {
        return priceRepositoryPort.findApplicablePrice(productId, brandId, date);
    }
}
