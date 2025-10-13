package com.example.appprueba.application.port.in;

import com.example.appprueba.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * =============================================================================
 * @Interface: PriceService
 * @Layer: Application Service (Use Case)
 * @Description: Provides business logic to retrieve applicable prices for
 *              a product and brand based on the provided date.
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
public interface PriceService {

    /**
     * Retrieves the applicable price for a given product, brand, and date.
     *
     * @param productId the product's ID
     * @param brandId the brand's ID
     * @param date the date
     * @return Optional of Price
     **/
    Optional<Price> getPriceByDateAndProductAndBrand(Long productId, Long brandId, LocalDateTime date);
}
