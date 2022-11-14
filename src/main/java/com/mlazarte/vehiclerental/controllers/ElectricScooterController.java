package com.mlazarte.vehiclerental.controllers;

import com.mlazarte.vehiclerental.dto.ElectricScooterDto;
import com.mlazarte.vehiclerental.exceptions.VehicleInUseException;
import com.mlazarte.vehiclerental.models.ElectricScooter;
import com.mlazarte.vehiclerental.services.ElectricScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ElectricScooterController {

    @Autowired
    private ElectricScooterService scooterService;

    @GetMapping(
            value = "/scooters",
            produces = "application/json"
    )
    public ResponseEntity<List<ElectricScooter>> getAllScooters() {
        List<ElectricScooter> scooters = scooterService.getAllScooters();
        return ResponseEntity.ok().body(scooters);
    }

    @GetMapping(
            value = "/scooters/{id}",
            produces = "application/json"
    )
    public ResponseEntity<ElectricScooter> getScooterById(@PathVariable Integer id) {
        ElectricScooter scooter = scooterService.getScooterById(id);
        if (scooter != null)
            return ResponseEntity.ok().body(scooter);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(
            value = "/scooters",
            produces = "application/json"
    )
    public ResponseEntity<ElectricScooter> saveScooter(@RequestBody ElectricScooterDto scooterDto) {
        ElectricScooter scooter = scooterService.saveNewScooter(scooterDto);
        URI uri = URI.create("/api/scooters/" + scooter.getVehicleId());

        return ResponseEntity.created(uri).body(scooter);
    }

    @PutMapping(
            value = "/scooters/{id}",
            produces = "application/json"
    )
    public ResponseEntity<ElectricScooter> updateScooter(
            @PathVariable Integer id,
            @RequestBody ElectricScooterDto scooterDto
    ) {
        ElectricScooter scooter = scooterService.updateScooter(id, scooterDto);
        if (scooter != null)
            return ResponseEntity.ok().body(scooter);
        else
            return ResponseEntity.notFound().build();
    }

    public ResponseEntity<ElectricScooter> deleteScooter(@PathVariable Integer id) {
        ElectricScooter scooter = scooterService.deleteScooter(id);
        if (scooter != null) {
            if (scooter.isAvailable())
                return ResponseEntity.ok().body(scooter);
            else
                throw new VehicleInUseException(
                        HttpStatus.BAD_REQUEST,
                        "The vehicle with id: " + id + "is used by a rental"
                );

        } else
            return ResponseEntity.notFound().build();
    }
}
