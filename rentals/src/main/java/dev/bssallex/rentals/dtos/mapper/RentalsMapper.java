package dev.bssallex.rentals.dtos.mapper;

import dev.bssallex.rentals.dtos.request.RentalsRequest;
import dev.bssallex.rentals.dtos.response.InstrumentResponse;
import dev.bssallex.rentals.dtos.response.RentalsResponse;
import dev.bssallex.rentals.dtos.response.UserResponse;
import dev.bssallex.rentals.entity.Instrument;
import dev.bssallex.rentals.entity.Rentals;
import dev.bssallex.rentals.entity.User;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RentalsMapper {

    public static Rentals toRequest(RentalsRequest request){
        Rentals rentals = Rentals.
                builder()
                .dateRentals(request.dateRentals())
                .dateDevolution(request.dateDevolution())
                .build();

        User user = new User();
        user.setId(request.userId());
        rentals.setUsers(user);

        List<Instrument> instruments = request.instrumentIds().stream()
                .map(id -> {
                    Instrument i = new Instrument();
                    i.setId(id);
                    return i;
                })
                .collect(Collectors.toList());
        rentals.setInstruments(instruments);

        return rentals;
    }

    public static RentalsResponse toResponse(Rentals rentals){

        UserResponse getUser = UserMapper.toResponse(rentals.getUsers());

        List<InstrumentResponse> getInstrument = rentals.getInstruments()
                .stream()
                .map(InstrumentMapper::toResponse)
                .toList();

        return RentalsResponse.
                builder()
                .id(rentals.getId())
                .tag(rentals.getTag())
                .dateRentals(rentals.getDateRentals())
                .dateDevolution(rentals.getDateDevolution())
                .userName(getUser)
                .instrumentIds(getInstrument)
                .build();
    }
}
