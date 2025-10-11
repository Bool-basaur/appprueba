package com.example.appprueba.adapters.out.jpa.repository;

import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

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
