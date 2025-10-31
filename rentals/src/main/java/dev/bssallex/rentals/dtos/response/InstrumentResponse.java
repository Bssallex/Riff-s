package dev.bssallex.rentals.dtos.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record InstrumentResponse(
        Long id,
        String brand,
        String model,
        String price,
        String typeInstrument,
        String available) {
}
