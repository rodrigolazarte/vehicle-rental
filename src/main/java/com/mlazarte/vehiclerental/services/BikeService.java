package com.mlazarte.vehiclerental.services;

import com.mlazarte.vehiclerental.dto.BikeDto;
import com.mlazarte.vehiclerental.models.Bike;
import com.mlazarte.vehiclerental.repositories.BikeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }


    public Bike getBikeById(Integer id) {
        Optional<Bike> bike = bikeRepository.findById(id);

        return bike.orElse(null);
    }


    public List<Bike> getBikesByFilter(String brandName, String bikeType, double tireSize) {
        Bike.BikeType type = Bike.BikeType.getByString(bikeType);
        System.out.println(type);

        return bikeRepository.findByFilters(brandName, type, tireSize);
    }


    public Bike saveNewBike(BikeDto bikeDto) {
        Bike bike = modelMapper.map(bikeDto, Bike.class);

        bike.setAvailable(true);
        bikeRepository.save(bike);

        return bike;
    }


    public Bike updateBike(Integer id, BikeDto bikeDto) {
        var oldBike = bikeRepository.findById(id);

        if (oldBike.isPresent()){
            var bike = modelMapper.map(bikeDto, Bike.class);

            bike.setVehicleId(oldBike.get().getVehicleId());
            bike.setAvailable(oldBike.get().isAvailable());
            bike.setRental(oldBike.get().getRental());
            bikeRepository.save(bike);

            return bike;
        } else
            return null;
    }


    public Bike deleteBike(Integer id) {
        Optional<Bike> bikeToDelete = bikeRepository.findById(id);

        if (bikeToDelete.isPresent()) {
            if (bikeToDelete.get().isAvailable()) {
                bikeRepository.delete(bikeToDelete.get());
            }
        } else
            return null;

        return bikeToDelete.get();
    }
}
