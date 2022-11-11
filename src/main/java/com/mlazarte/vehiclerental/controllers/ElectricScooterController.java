package com.mlazarte.vehiclerental.controllers;

import com.mlazarte.vehiclerental.models.ElectricScooter;
import com.mlazarte.vehiclerental.repositories.ElectricScooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scooters")
@CrossOrigin
public class ElectricScooterController {

    @Autowired
    private ElectricScooterRepository repository;

    @GetMapping(
            produces = "application/json"
    )
    public ResponseEntity<List<ElectricScooter>> getAllScooters() {
        List<ElectricScooter> scooters = repository.findAll();
        return ResponseEntity.ok().body(scooters);
    }

    @GetMapping(
            value = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<ElectricScooter> getScooterById(@PathVariable Integer id) {
        Optional<ElectricScooter> scooter = repository.findById(id);
        if (scooter.isPresent()) return ResponseEntity.ok().body(scooter.get());
        else return ResponseEntity.notFound().build();
    }

    @PostMapping(
            produces = "application/json"
    )
    public ResponseEntity<ElectricScooter> saveBike(@RequestBody ElectricScooter scooter) {
        repository.save(scooter);
        URI uri = URI.create("/api/scooters/" + scooter.getVehicleId());
        return ResponseEntity.created(uri).body(scooter);
    }
}
