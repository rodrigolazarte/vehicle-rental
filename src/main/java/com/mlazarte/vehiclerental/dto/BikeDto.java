package com.mlazarte.vehiclerental.dto;

import com.mlazarte.vehiclerental.models.Bike;

public class BikeDto {

    private int vehicleId;
    private double tireSize;
    private String description;
    private Bike.BikeType bikeType;
    private String brandName;
    private double pricePerHour;
    private double pricePerDay;

    public BikeDto() {
    }

    public BikeDto(int vehicleId, double tireSize, String description, Bike.BikeType bikeType,
                   String brandName, double pricePerHour, double pricePerDay) {
        this.vehicleId = vehicleId;
        this.tireSize = tireSize;
        this.description = description;
        this.bikeType = bikeType;
        this.brandName = brandName;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getTireSize() {
        return tireSize;
    }

    public void setTireSize(double tireSize) {
        this.tireSize = tireSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bike.BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(Bike.BikeType bikeType) {
        this.bikeType = bikeType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
