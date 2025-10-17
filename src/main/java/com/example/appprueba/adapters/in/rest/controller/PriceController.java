package com.example.appprueba.adapters.in.rest.controller;

import com.example.appprueba.adapters.in.rest.dto.PriceResponseDTO;
import com.example.appprueba.adapters.in.rest.exception.PriceNotFoundException;
import com.example.appprueba.application.port.in.PriceService;
import com.example.appprueba.adapters.in.rest.mapper.PriceRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * =============================================================================
 * @Class: PriceController
 * @Layer: Inbound Adapter (REST)
 * @Description: Exposes HTTP GET endpoint to query the applicable price
 *               for a given product and brand at a specific date/time.
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;
    private final PriceRestMapper priceRestMapper;

    /**
     *   Retrieves the applicable price based on product ID, brand ID, and date.
     *
     * @param date the date
     * @param brandId the brand's ID
     * @param productId the product's ID
     * @Return ResponseEntity of PriceResponseDTO. It returns the price if it was found,
     *         with a 200 response. If price was not found, it returns a PriceNotFoundException.
     **/
    @GetMapping
    public ResponseEntity<PriceResponseDTO> getPriceByDateAndProductAndBrand(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Long brandId,
            @RequestParam Long productId
    ) {
        return priceService.getPriceByDateAndProductAndBrand(productId, brandId, date)
                .map(priceRestMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow(PriceNotFoundException::new);
    }
}
