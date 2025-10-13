package com.example.appprueba.adapters.out.jpa.repository;

import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

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
     * @param applicationDate the date
     * @return List of PriceEntity
     **/
    @Query("""
    SELECT p FROM PriceEntity p
    WHERE p.productId = :productId
      AND p.brandId = :brandId
      AND :applicationDate BETWEEN p.startDate AND p.endDate
    ORDER BY p.priority DESC
    """)
    List<PriceEntity> findTopByProductAndBrandAndDateOrderByPriorityDesc(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("applicationDate") LocalDateTime applicationDate
    );

}
