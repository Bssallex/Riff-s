package dev.bssallex.rentals.dtos.mapper;

import dev.bssallex.rentals.dtos.request.InstrumentRequest;
import dev.bssallex.rentals.dtos.response.InstrumentResponse;
import dev.bssallex.rentals.entity.Instrument;
import dev.bssallex.rentals.enums.Available;
import dev.bssallex.rentals.enums.TypeInstrument;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@UtilityClass
public class InstrumentMapper {

    private static String formatPrice(BigDecimal bigDecimal){
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return format.format(bigDecimal);
    }

    public static Instrument toRequest(InstrumentRequest request){
        return Instrument.builder()
                .brand(request.brand())
                .model(request.model())
                .price(request.price())
                .typeInstrument(TypeInstrument.valueOf(request.typeInstrument().toUpperCase()))
                .available(Available.valueOf(request.available().toUpperCase()))
                .build();
    }

    public static InstrumentResponse toResponse(Instrument instrument){
        return InstrumentResponse.builder()
                .id(instrument.getId())
                .brand(instrument.getBrand())
                .model(instrument.getModel())
                .price(formatPrice(instrument.getPrice()))
                .typeInstrument(instrument.getTypeInstrument().name())
                .available(instrument.getAvailable().name())
                .build();
    }
}
