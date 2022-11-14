package com.mlazarte.vehiclerental.controllers;

import com.mlazarte.vehiclerental.dto.BikeDto;
import com.mlazarte.vehiclerental.exceptions.VehicleInUseException;
import com.mlazarte.vehiclerental.models.Bike;
import com.mlazarte.vehiclerental.services.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping(
            value = "/bikes",
            produces = "application/json"
    )
    public ResponseEntity<List<Bike>> getAllBikes() {
        List<Bike> bikes = bikeService.getAllBikes();
        return ResponseEntity.ok().body(bikes);
    }

    @GetMapping(
            value = "/bikes/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Bike> getBikeById(@PathVariable Integer id) {
        Bike bike = bikeService.getBikeById(id);
        if (bike != null)
            return ResponseEntity.ok().body(bike);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(
            value = "/bikes",
            produces = "application/json"
    )
    public ResponseEntity<Bike> saveBike(@RequestBody BikeDto bikeDto) {
        Bike bike = bikeService.saveNewBike(bikeDto);
        URI uri = URI.create("/api/bikes/" + bike.getVehicleId());

        return ResponseEntity.created(uri).body(bike);
    }

    @GetMapping(
            value = "/bikes/filter",
            produces = "application/json"
    )
    public ResponseEntity<List<Bike>> getBikesByFilter(
            @RequestParam(required = false, name = "type", defaultValue = "") String bikeType,
            @RequestParam(required = false, name = "tireSize", defaultValue = "0.0") double tireSize,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand
    ) {
        List<Bike> bikes = bikeService.getBikesByFilter(brand, bikeType, tireSize);

        return ResponseEntity.ok().body(bikes);
    }

    @PutMapping(
            value = "/bikes/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Bike> updateBike(
            @PathVariable Integer id,
            @RequestBody BikeDto bikeDto
    ) {
        Bike updatedBike = bikeService.updateBike(id, bikeDto);
        if (updatedBike != null)
            return ResponseEntity.ok().body(updatedBike);
        else
            return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(
            value = "/bikes/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Object> deleteBike(@PathVariable("id") Integer bikeId) {
        Bike bike = bikeService.deleteBike(bikeId);
        if (bike != null)
            if (bike.isAvailable())
                return ResponseEntity.ok().body(bike);
            else
                throw new VehicleInUseException(
                        HttpStatus.BAD_REQUEST,
                        String.format("The bike with the id %d is currently used in a rental", bikeId)
                );
        else
            return ResponseEntity.notFound().build();
    }
}
