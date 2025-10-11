package com.example.appprueba.adapters.out.jpa.mapper;

import com.example.appprueba.adapters.in.rest.dto.PriceResponseDTO;
import com.example.appprueba.adapters.out.jpa.entity.PriceEntity;
import com.example.appprueba.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(source = "curr", target = "currency")
    Price toDomain(PriceEntity entity);

    @Mapping(source = "currency", target = "curr")
    PriceEntity toEntity(Price price);

    PriceResponseDTO toDto(Price price);
}