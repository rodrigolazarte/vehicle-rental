package com.mlazarte.vehiclerental.services;

import com.mlazarte.vehiclerental.dto.RentalDto;
import com.mlazarte.vehiclerental.models.Rental;
import com.mlazarte.vehiclerental.repositories.BikeRepository;
import com.mlazarte.vehiclerental.repositories.ClientRepository;
import com.mlazarte.vehiclerental.repositories.ElectricScooterRepository;
import com.mlazarte.vehiclerental.repositories.MotorcycleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RentalService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private ElectricScooterRepository electricScooterRepository;

    public Rental createNewRental(RentalDto rentalDto) {
        Rental rental = modelMapper.map(rentalDto, Rental.class);
        int clientId = rentalDto.getClientId();


        SEARCH:
        for (int vehicleId : rentalDto.getVehicleIds()) {
            System.out.println("Checking if it is a bike");
            if (bikeRepository.existsById(vehicleId)) {
                var bike = bikeRepository.findById(vehicleId).get();
                bike.setAvailable(false);
                rental.addVehicle(bike);
                continue SEARCH;
            }

            System.out.println("Checking if it is a motorcycle");
            if (motorcycleRepository.existsById(vehicleId)) {
                var motorcycle = motorcycleRepository.findById(vehicleId).get();
                motorcycle.setAvailable(false);
                rental.addVehicle(motorcycle);
                continue SEARCH;
            }

            System.out.println("Checking if it is a scooter");
            if (electricScooterRepository.existsById(vehicleId)) {
                var electricScooter = electricScooterRepository.findById(vehicleId).get();
                electricScooter.setAvailable(false);
                rental.addVehicle(electricScooter);
                continue SEARCH;
            }
        }

        if (clientRepository.existsById(clientId)) {
            rental.setClient(clientRepository.findById(clientId).get());
        }

        rental.setRentalPrice(calculateRentalPrice(rental));
        rental.setRentalStatus(Rental.RentalStatus.ACTIVE);

        return rental;
    }

    private double calculateRentalPrice(Rental rental) {
        double totalRentalPrice = 0.0;
        Duration duration = Duration.between(rental.getPickUpDateTime(), rental.getReturnDateTime());

        switch (rental.getType()) {
            case HOURLY, FREE -> {
                for (var vehicle : rental.getVehicles()) {
                    totalRentalPrice += vehicle.getPricePerHour() * duration.toHours();
                }
            }
            case DAILY -> {
                for (var vehicle : rental.getVehicles()) {
                    totalRentalPrice += vehicle.getPricePerDay() * duration.toDays();
                }
            }
        }

        return totalRentalPrice;
    }
}

