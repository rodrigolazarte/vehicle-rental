package com.mlazarte.vehiclerental.controllers;

import com.mlazarte.vehiclerental.dto.MotorcycleDto;
import com.mlazarte.vehiclerental.exceptions.VehicleInUseException;
import com.mlazarte.vehiclerental.models.Motorcycle;
import com.mlazarte.vehiclerental.services.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;

    @GetMapping(
            value = "/motorcycles",
            produces = "application/json"
    )
    public ResponseEntity<List<Motorcycle>> getAllMotorcycles() {
        List<Motorcycle> motorcycles = motorcycleService.getAllMotorcycles();
        return ResponseEntity.ok().body(motorcycles);
    }

    @GetMapping(
            value = "/motorcycles/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Motorcycle> getMotorcycleById(@PathVariable Integer id) {
        Motorcycle motorcycle = motorcycleService.getMotorcycleById(id);

        if (motorcycle != null)
            return ResponseEntity.ok().body(motorcycle);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(
            value = "/motorcycles",
            produces = "application/json"
    )
    public ResponseEntity<Motorcycle> saveMotorcycle(@RequestBody MotorcycleDto motorcycleDto) {
        Motorcycle motorcycle = motorcycleService.saveNewMotorcycle(motorcycleDto);
        URI uri = URI.create("/api/motorcycles/" + motorcycle.getVehicleId());
        return ResponseEntity.created(uri).body(motorcycle);
    }

    @PutMapping(
            value = "/motorcycles/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Motorcycle> updateMortorcycle(
            @PathVariable Integer id,
            @RequestBody MotorcycleDto motorcycleDto
    ) {
        Motorcycle motorcycle = motorcycleService.updateMotorcycle(id, motorcycleDto);
        if (motorcycle != null)
            return ResponseEntity.ok().body(motorcycle);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping(
            value = "/motorcycles/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Motorcycle> deleteMotorcycle(@PathVariable Integer id) {
        Motorcycle motorcycle = motorcycleService.deleteMotorcycle(id);
        if (motorcycle != null)
            if (motorcycle.isAvailable())
                return ResponseEntity.ok().body(motorcycle);
            else
                throw new VehicleInUseException(
                        HttpStatus.BAD_REQUEST,
                        "The motorcycle with id " + motorcycle.getVehicleId() + " is used in a rental."
                );
        else
            return ResponseEntity.notFound().build();
    }
}
