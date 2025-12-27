package dev.bssallex.rentals.Controller;

import dev.bssallex.rentals.Service.InstrumentService;
import dev.bssallex.rentals.docs.InstrumentControllerDoc;
import dev.bssallex.rentals.dtos.request.InstrumentRequest;
import dev.bssallex.rentals.dtos.response.InstrumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instruments")
@RequiredArgsConstructor
public class InstrumentController implements InstrumentControllerDoc {

    private final InstrumentService service;

    @GetMapping("/all")
    public ResponseEntity<List<InstrumentResponse>> listAllInstruments(){
        List<InstrumentResponse> instruments = service.findAllInstruments();
        return ResponseEntity.ok(instruments);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<InstrumentResponse> getInstrumentById(@PathVariable Long id){
        InstrumentResponse instrument = service.findInstrumentById(id);
        return ResponseEntity.ok(instrument);
    }

    @PostMapping("/create")
    public ResponseEntity<InstrumentResponse> createInstrument(@RequestBody InstrumentRequest request){
        InstrumentResponse created = service.createInstrument(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InstrumentResponse> updateInstrument(@PathVariable Long id, @RequestBody InstrumentRequest request){
        InstrumentResponse updated = service.updateInstrument(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInstrument(@PathVariable Long id){
        service.deleteInstrument(id);
        return ResponseEntity.noContent().build();
    }
}
