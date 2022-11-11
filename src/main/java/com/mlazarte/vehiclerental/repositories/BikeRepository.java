package com.mlazarte.vehiclerental.repositories;

import com.mlazarte.vehiclerental.models.Bike;
import com.mlazarte.vehiclerental.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BikeRepository extends JpaRepository<Bike, Integer> {
    List<Bike> findByBikeType(Bike.BikeType type);

    List<Bike> findByTireSize(double tireSize);

    @Query(value = "SELECT v FROM Vehicle v")
    List<Vehicle> getAllVehicles();

    @Query(
            value = "SELECT b FROM Bike b " +
                    "WHERE (:brandName = '' OR b.brandName LIKE :brandName) " +
                    "AND (:bikeType is null OR b.bikeType LIKE :bikeType) " +
                    "AND b.tireSize >= :tireSize"
    )
    List<Bike> findByFilters(@Param("brandName") String brandName,
                             @Param("bikeType") Bike.BikeType bikeType,
                             @Param("tireSize") double tireSize
    );
}
