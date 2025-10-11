package com.example.appprueba.adapters.out.jpa.repository;

import com.example.appprueba.domain.model.Price;
import com.example.appprueba.application.port.out.PriceRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository jpaRepository;

    @Override
    public Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return Optional.empty(); //TODO llamar al jpa repository
    }
}
