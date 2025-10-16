package com.example.appprueba.mapper.persistence;

import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import com.example.appprueba.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * =============================================================================
 * @Interface: PricePersistenceMapper
 * @Layer: Cross-layer Utility (Mapping)
 * @Description: Converts persistence entities and domain models by
 *              using MapStruct.
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
@Mapper(componentModel = "spring")
public interface PricePersistenceMapper {
    @Mapping(source = "curr", target = "currency")
    Price toDomain(PriceEntity entity);

    @Mapping(source = "currency", target = "curr")
    PriceEntity toEntity(Price price);
}
