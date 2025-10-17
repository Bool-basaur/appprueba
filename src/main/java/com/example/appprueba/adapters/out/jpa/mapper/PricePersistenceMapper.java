package com.example.appprueba.adapters.out.jpa.mapper;

import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import com.example.appprueba.domain.model.Prices;
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
    Prices toDomain(PriceEntity entity);

}
