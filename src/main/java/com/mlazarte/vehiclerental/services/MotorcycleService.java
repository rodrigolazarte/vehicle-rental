package com.mlazarte.vehiclerental.services;

import com.mlazarte.vehiclerental.dto.MotorcycleDto;
import com.mlazarte.vehiclerental.models.Motorcycle;
import com.mlazarte.vehiclerental.repositories.MotorcycleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleService {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Motorcycle> getAllMotorcycles(){
        return motorcycleRepository.findAll();
    }

    public Motorcycle getMotorcycleById(Integer id){
        Optional<Motorcycle> motorcycle = motorcycleRepository.findById(id);

        return motorcycle.orElse(null);
    }

    public Motorcycle saveNewMotorcycle(MotorcycleDto motorcycleDto){
        Motorcycle motorcycle = modelMapper.map(motorcycleDto, Motorcycle.class);
        motorcycle.setAvailable(true);
        motorcycleRepository.save(motorcycle);

        return motorcycle;
    }
}
