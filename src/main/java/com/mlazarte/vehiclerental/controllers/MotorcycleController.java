package com.mlazarte.vehiclerental.controllers;

import com.mlazarte.vehiclerental.models.Motorcycle;
import com.mlazarte.vehiclerental.repositories.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/motorcycles")
@CrossOrigin
public class MotorcycleController {
    @Autowired
    private MotorcycleRepository repository;

    @GetMapping(
            produces = "application/json"
    )
    public ResponseEntity<List<Motorcycle>> getAllMotorcycles() {
        List<Motorcycle> motorcycles = repository.findAll();
        return ResponseEntity.ok().body(motorcycles);
    }

    @GetMapping(
            value = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Motorcycle> getMotorcycleById(@PathVariable Integer id) {
        Optional<Motorcycle> motorcycle = repository.findById(id);
        if (motorcycle.isPresent()) return ResponseEntity.ok().body(motorcycle.get());
        else return ResponseEntity.notFound().build();
    }

    @PostMapping(
            produces = "application/json"
    )
    public ResponseEntity<Motorcycle> saveMotorcycle(@RequestBody Motorcycle motorcycle) {
        repository.save(motorcycle);
        URI uri = URI.create("/api/motorcycles/" + motorcycle.getVehicleId());
        return ResponseEntity.created(uri).body(motorcycle);
    }
}
