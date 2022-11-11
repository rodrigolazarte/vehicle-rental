package com.mlazarte.vehiclerental.models;

import javax.persistence.*;

@Entity
@Table(name = "BIKES")
public class Bike extends Vehicle {

    @Column(name = "tire_size")
    private double tireSize;

    @Enumerated(EnumType.STRING)
    private BikeType bikeType;

    public enum BikeType {
        ROAD,
        MOUNTAIN,
        HYBRID,
        ELECTRIC,
        TOURING;

        public static BikeType getByString(String bikeType) {
            try {
                return BikeType.valueOf(bikeType);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    public Bike() {
    }

    public Bike(double tireSize, BikeType bikeType) {
        this.tireSize = tireSize;
        this.bikeType = bikeType;
    }

    public double getTireSize() {
        return tireSize;
    }

    public void setTireSize(double tireSize) {
        this.tireSize = tireSize;
    }

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }
}
