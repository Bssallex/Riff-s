package dev.bssallex.rentals.Service;

import dev.bssallex.rentals.Repository.InstrumentRepository;
import dev.bssallex.rentals.dtos.mapper.InstrumentMapper;
import dev.bssallex.rentals.dtos.request.InstrumentRequest;
import dev.bssallex.rentals.dtos.response.InstrumentResponse;
import dev.bssallex.rentals.entity.Instrument;
import dev.bssallex.rentals.enums.Available;
import dev.bssallex.rentals.enums.TypeInstrument;
import dev.bssallex.rentals.exceptions.InstrumentNotFound;
import dev.bssallex.rentals.exceptions.ValueEnumInvalid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrumentService {

    private final InstrumentRepository repository;

    public List<InstrumentResponse> findAllInstruments(){
        List<Instrument> allInstruments = repository.findAll();
        return allInstruments
                .stream()
                .map(InstrumentMapper::toResponse)
                .toList();
    }

    public InstrumentResponse findInstrumentById(Long id){
        Instrument existingInstrument = repository.findById(id).orElseThrow(() -> new InstrumentNotFound("Instrumento com ID " + id + " não encontrado."));
        return InstrumentMapper.toResponse(existingInstrument);
    }

    public InstrumentResponse createInstrument(InstrumentRequest request){

        try{
           TypeInstrument.valueOf(request.typeInstrument());
        }catch (IllegalArgumentException e){
            throw new ValueEnumInvalid(String.format("Valor '%s' é inválido para o campo 'typeInstrument'. Valores aceitos: %s",
                    request.typeInstrument(), Arrays.toString(TypeInstrument.values())));
        }

        try{
            Available.valueOf(request.available());
        }catch (IllegalArgumentException e){
             throw new ValueEnumInvalid(String.format("Valor '%s' é inválido para o campo 'available'. Valores aceitos: %s",
            request.available(), Arrays.toString(Available.values())));
        }

        Instrument savedInstrument = repository.save(InstrumentMapper.toRequest(request));
        return InstrumentMapper.toResponse(savedInstrument);
    }

    public InstrumentResponse updateInstrument(Long id, InstrumentRequest request){
        Instrument existingInstrument = repository.findById(id).orElseThrow(() -> new InstrumentNotFound("Instrumento com ID " + id + " não encontrado."));

        existingInstrument.setBrand(request.brand());
        existingInstrument.setModel(request.model());
        existingInstrument.setPrice(request.price());
        existingInstrument.setTypeInstrument(TypeInstrument.valueOf(request.typeInstrument().toUpperCase()));
        existingInstrument.setAvailable(Available.valueOf(request.available().toUpperCase()));

        Instrument updatedInstrument = repository.save(existingInstrument);
        return InstrumentMapper.toResponse(updatedInstrument);
    }

    public void deleteInstrument(Long id){
        Instrument existingInstrument = repository.findById(id).orElseThrow(() -> new InstrumentNotFound("Instrumento com ID " + id + " não encontrado."));
        repository.delete(existingInstrument);
    }
}
