package com.example.appprueba.adapters.out.jpa.repository;

import com.example.appprueba.domain.model.Price;
import com.example.appprueba.application.port.out.PriceRepositoryPort;
import com.example.appprueba.mapper.persistence.PricePersistenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * =============================================================================
 * @Class: PriceRepositoryAdapter
 * @Layer: Outbound Adapter (Persistence)
 * @Description: Implements the PriceRepositoryPort interface inr order to
 *              interact with the data source using PriceJpaRepository and maps
 *              entities to domain models.
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
@Repository
@AllArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository jpaRepository;
    private final PricePersistenceMapper pricePersistenceMapper;

    /**
     * Retrieves the applicable price based on product ID, brand ID, and date.
     * Returns the first Price that was found.
     *
     * @param productId the product's ID
     * @param brandId the brand's ID
     * @param applicationDate the date
     * @return Optional of Price.
     **/
    @Override
    public Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return jpaRepository
                .findTopByProductAndBrandAndDateOrderByPriorityDesc(productId, brandId, applicationDate)
                .stream()
                .findFirst()
                .map(pricePersistenceMapper::toDomain);
    }
}
