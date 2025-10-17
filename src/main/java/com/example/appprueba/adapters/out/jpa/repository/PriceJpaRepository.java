package com.example.appprueba.adapters.out.jpa.repository;

import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * =============================================================================
 * @Interface: PriceJpaRepository
 * @Layer: Outbound Adapter (Persistence) — Repository
 * @Description: Extends JpaRepository to interact with the PriceEntity
 *              in the database.
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Custom query that finds the applicable price for a given product, brand, and date.
     * The results are ordered by priority (descending).
     *
     * @param productId the product's ID
     * @param brandId the brand's ID
     * @param startDate the date (in order to set the minimum limit)
     * @param endDate the date  (in order to set the maximum limit)
     * @return Optional of PriceEntity
     **/
    Optional<PriceEntity> findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long productId,
            Long brandId,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

}
