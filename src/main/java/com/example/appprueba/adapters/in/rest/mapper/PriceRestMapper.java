package com.example.appprueba.adapters.in.rest.mapper;

import com.example.appprueba.adapters.in.rest.dto.PriceResponseDTO;
import com.example.appprueba.domain.model.Prices;
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

    PriceResponseDTO toDto(Prices price);

    Prices toDomain(PriceResponseDTO dto);
}
