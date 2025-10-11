package com.example.appprueba.application.service.impl;

import com.example.appprueba.application.port.in.PriceService;
import com.example.appprueba.application.port.out.PriceRepositoryPort;
import com.example.appprueba.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Price getPriceByDateAndProductAndBrand(LocalDateTime date, Long productId, Long brandId) {
        return null; //TODO llama al repositoryport
    }
}
