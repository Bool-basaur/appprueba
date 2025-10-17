package com.example.appprueba.application.port.out;

import com.example.appprueba.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * =============================================================================
 * @Interface: PriceRepositoryPort
 * @Layer: Application Port (Outbound)
 * @Description: Defines the contract for the repository layer in order to
 *              fetch applicable prices from the data source.
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
public interface PriceRepositoryPort {

    /**
     * Fetches the applicable price for a given product, brand, and date.
     *
     * @param productId the product's ID
     * @param brandId the brand's ID
     * @param applicationDate the date
     * @return Optional of Price
     **/
    Optional<Prices> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
