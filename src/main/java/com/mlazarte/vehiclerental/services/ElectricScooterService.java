package com.mlazarte.vehiclerental.services;

import com.mlazarte.vehiclerental.dto.ElectricScooterDto;
import com.mlazarte.vehiclerental.models.ElectricScooter;
import com.mlazarte.vehiclerental.repositories.ElectricScooterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectricScooterService {

    @Autowired
    private ElectricScooterRepository scooterRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ElectricScooter> getAllScooters() {
        return scooterRepository.findAll();
    }

    public ElectricScooter getScooterById(Integer id) {
        Optional<ElectricScooter> optionalScooter = scooterRepository.findById(id);

        return optionalScooter.orElse(null);
    }

    public ElectricScooter saveNewScooter(ElectricScooterDto electricScooterDto) {
        ElectricScooter electricScooter = modelMapper.map(electricScooterDto, ElectricScooter.class);
        scooterRepository.save(electricScooter);

        return electricScooter;
    }

    public ElectricScooter updateScooter(Integer id, ElectricScooterDto electricScooterDto) {
        var oldScooter = scooterRepository.findById(id);

        if(oldScooter.isPresent()) {
            ElectricScooter updatedScooter = modelMapper.map(electricScooterDto, ElectricScooter.class);
            updatedScooter.setAvailable(oldScooter.get().isAvailable());
            updatedScooter.setRental(oldScooter.get().getRental());
            updatedScooter.setVehicleId(oldScooter.get().getVehicleId());

            scooterRepository.save(updatedScooter);

            return updatedScooter;
        } else
            return null;
    }

    public ElectricScooter deleteScooter(Integer id){
        Optional<ElectricScooter> scooterToDelete = scooterRepository.findById(id);

        if (scooterToDelete.isPresent()) {
            if (scooterToDelete.get().isAvailable()) {
                scooterRepository.delete(scooterToDelete.get());
            }
        } else
            return null;

        return scooterToDelete.get();
    }
}
