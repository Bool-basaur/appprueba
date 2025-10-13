package com.example.appprueba.application.service.impl;

import com.example.appprueba.application.port.in.PriceService;
import com.example.appprueba.application.port.out.PriceRepositoryPort;
import com.example.appprueba.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * =============================================================================
 * @Class: PriceServiceImpl
 * @Layer: Application Service (Use Case) — Implementation
 * @Description: Implements the PriceService interface. It has the business logic
 *              for retrieving prices based on product, brand, and date.
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRepositoryPort priceRepositoryPort;

    /**
     * Retrieves the applicable price for a given product, brand, and date.
     *
     * @param productId the product's ID
     * @param brandId the brand's ID
     * @param date the date for which the price is applicable
     * @return Optional of Price
     **/
    @Override
    public Optional<Price> getPriceByDateAndProductAndBrand(Long productId, Long brandId, LocalDateTime date) {
        return priceRepositoryPort.findApplicablePrice(productId, brandId, date);
    }
}
