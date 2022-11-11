package com.mlazarte.vehiclerental.repositories;

import com.mlazarte.vehiclerental.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query("SELECT r FROM Rental r INNER JOIN Vehicle v ON v.rental = r WHERE v.vehicleId = :id")
    Optional<Rental> findByRentalId(@Param("id") Integer id);

}
