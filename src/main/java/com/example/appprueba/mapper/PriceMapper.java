package com.example.appprueba.mapper;

import com.example.appprueba.adapters.in.rest.dto.PriceResponseDTO;
import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import com.example.appprueba.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * =============================================================================
 * @Interface: PriceMapper
 * @Layer: Cross-layer Utility (Mapping)
 * @Description: Converts persistence entities, domain models, and DTOs by
 *              using MapStruct.
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(source = "curr", target = "currency")
    Price toDomain(PriceEntity entity);

    @Mapping(source = "currency", target = "curr")
    PriceEntity toEntity(Price price);

    PriceResponseDTO toDto(Price price);
}