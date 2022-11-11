package com.mlazarte.vehiclerental.repositories;

import com.mlazarte.vehiclerental.models.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Integer> {
}
