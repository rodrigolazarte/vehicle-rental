package com.mlazarte.vehiclerental.controllers;

import com.mlazarte.vehiclerental.dto.RentalDto;
import com.mlazarte.vehiclerental.models.Rental;
import com.mlazarte.vehiclerental.repositories.RentalRepository;
import com.mlazarte.vehiclerental.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RentalService rentalService;

    @PostMapping(
            value = "/rentals",
            produces = "application/json"
    )
    public ResponseEntity<Rental> saveNewRental(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalService.createNewRental(rentalDto);
        rentalRepository.save(rental);
        URI uri = URI.create("/rentals/" + rental.getRentalId());
        return ResponseEntity.created(uri).body(rental);
    }

    @GetMapping(
            value = "/rentals",
            produces = "application/json"
    )
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return ResponseEntity.ok().body(rentals);
    }

    @GetMapping(
            value = "/rentals/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Rental> getRentalById(@PathVariable Integer id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isPresent())
            return ResponseEntity.ok().body(rental.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping(
            value = "/rentals/find-by-vehicle-id/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Rental> getRentalByVehicleId(@PathVariable Integer id) {
        Optional<Rental> rental = rentalRepository.findByRentalId(id);
        if (rental.isPresent())
            return ResponseEntity.ok().body(rental.get());
        else return ResponseEntity.notFound().build();
    }
}
