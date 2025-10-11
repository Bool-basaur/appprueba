package com.example.appprueba.adapters.in.rest.Controller;


import com.example.appprueba.adapters.in.rest.dto.PriceResponseDTO;
import com.example.appprueba.application.port.in.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping
    public ResponseEntity<PriceResponseDTO> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Long brandId,
            @RequestParam Long productId
    ) { //TODO llama al servicio
        return ResponseEntity.ok(PriceResponseDTO.builder().build());
    }
}
