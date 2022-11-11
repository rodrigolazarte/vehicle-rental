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


    public Bike updateBike(BikeDto bikeDto) {
        var oldBike = bikeRepository.findById(bikeDto.getVehicleId()).get();
        var bike = modelMapper.map(bikeDto, Bike.class);

        bike.setRental(oldBike.getRental());
        bikeRepository.save(bike);

        return bike;
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
