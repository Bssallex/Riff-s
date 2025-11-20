package dev.bssallex.rentals.Service;

import dev.bssallex.rentals.Repository.InstrumentRepository;
import dev.bssallex.rentals.Repository.RentalsRepository;
import dev.bssallex.rentals.Repository.UserRepository;
import dev.bssallex.rentals.configuration.EmailDto;
import dev.bssallex.rentals.configuration.TextEmail;
import dev.bssallex.rentals.configuration.producer.RentalsProducer;
import dev.bssallex.rentals.dtos.mapper.RentalsMapper;
import dev.bssallex.rentals.dtos.request.RentalsRequest;
import dev.bssallex.rentals.dtos.response.RentalsResponse;
import dev.bssallex.rentals.entity.Instrument;
import dev.bssallex.rentals.entity.Rentals;
import dev.bssallex.rentals.entity.User;
import dev.bssallex.rentals.enums.Available;
import dev.bssallex.rentals.enums.EmailStatus;
import dev.bssallex.rentals.enums.TypeInstrument;
import dev.bssallex.rentals.exceptions.InstrumentNotFound;
import dev.bssallex.rentals.exceptions.NotFoundUserRentals;
import dev.bssallex.rentals.exceptions.UserNotFound;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalsService {

    private final RentalsRepository repository;
    private final UserRepository userRepository;
    private final InstrumentRepository instrumentRepository;
    private final RentalsProducer producer;

    public List<RentalsResponse> listAllRentals(){
        List<Rentals> rentals = repository.findAll();
        return rentals
                .stream()
                .map(RentalsMapper::toResponse)
                .toList();
    }

    public RentalsResponse getRentalsByNameUser(String name){
        Rentals rentals = repository.findByUsers_name(name)
                .orElseThrow(() -> new NotFoundUserRentals("Usuário não encontrado."));
        return RentalsMapper.toResponse(rentals);
    }

    public String gerarPrefixo(String type) {
        return switch (type.toUpperCase()) {
            case "GUITARRA" -> "GTR";
            case "BAIXO" -> "BASS";
            case "VIOLAO" -> "VLN";
            case "TECLADO" -> "KEY";
            case "BATERIA" -> "DRUM";
            default -> "UNK";
        };
    }

    @Transactional
    public RentalsResponse createRentals(RentalsRequest request){

        User user = userRepository.findById(request.userId()).orElseThrow(() -> new UserNotFound("Usuário não encontrado."));

        List<Instrument> instrument = instrumentRepository.findAllById(request.instrumentIds());
        if(instrument.isEmpty()){
            throw new InstrumentNotFound("Instrumento não encontrado.");
        }

        instrument.forEach(i -> i.setAvailable(Available.INDISPONIVEL));

        TypeInstrument type = instrument.get(0).getTypeInstrument();
        String prefixo = gerarPrefixo(type.name());
        long count = repository.countByInstruments_TypeInstrument(type) + 1;
        String numero = String.format("%03d", count);
        String userName = user.getName().toUpperCase().replace(" ", "");
        String tag = userName + "-" + prefixo + "-" + numero;

        Rentals rentals = RentalsMapper.toRequest(request);
        rentals.setTag(tag);
        rentals.setUsers(user);
        rentals.setInstruments(instrument);

        var emailDto = new EmailDto();
        emailDto.setEmailTo(user.getEmail());
        emailDto.setEmailSubject("Bem-vindo!");
        emailDto.setBody(TextEmail.gerarEmail(user, rentals, tag, instrument));
        emailDto.setSendDateEmail(LocalDate.now());
        emailDto.setStatusEmail(EmailStatus.SENT);

        Rentals savedRentals = repository.save(rentals);
        producer.publishEvent(emailDto); // 4
        return RentalsMapper.toResponse(savedRentals);
    }


}
