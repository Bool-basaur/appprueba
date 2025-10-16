package com.example.appprueba.mapper.rest;

import com.example.appprueba.adapters.in.rest.dto.PriceResponseDTO;
import com.example.appprueba.domain.model.Price;
import org.mapstruct.Mapper;

/**
 * =============================================================================
 * @Interface: PriceRestMapper
 * @Layer: Cross-layer Utility (Mapping)
 * @Description: Converts dtos by using MapStruct.
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
@Mapper(componentModel = "spring")
public interface PriceRestMapper {

    PriceResponseDTO toDto(Price price);

    Price toDomain(PriceResponseDTO dto);
}
