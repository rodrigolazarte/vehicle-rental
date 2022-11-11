package com.mlazarte.vehiclerental.repositories;

import com.mlazarte.vehiclerental.models.ElectricScooter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricScooterRepository extends JpaRepository<ElectricScooter, Integer> {

}
