package dev.bssallex.rentals.Controller;

import dev.bssallex.rentals.Service.RentalsService;
import dev.bssallex.rentals.dtos.request.RentalsRequest;
import dev.bssallex.rentals.dtos.response.RentalsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalsController {

    private final RentalsService service;

    @GetMapping("/all")
    public ResponseEntity<List<RentalsResponse>> findAllRentals(){
        List<RentalsResponse> rentals = service.listAllRentals();
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<RentalsResponse> findRentalsByNameUser(@PathVariable String name){
        RentalsResponse rentals = service.getRentalsByNameUser(name);
        return ResponseEntity.ok(rentals);
    }

    @PostMapping("/create")
    public ResponseEntity<RentalsResponse> createRentals(@RequestBody RentalsRequest request){
        RentalsResponse rentals = service.createRentals(request);
        return ResponseEntity.ok(rentals);
    }
}
