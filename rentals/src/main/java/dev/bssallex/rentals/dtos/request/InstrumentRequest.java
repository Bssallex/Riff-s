package dev.bssallex.rentals.dtos.request;

import java.math.BigDecimal;

public record InstrumentRequest(
        String brand,
        String model,
        BigDecimal price,
        String typeInstrument,
        String available
        ) {
}
