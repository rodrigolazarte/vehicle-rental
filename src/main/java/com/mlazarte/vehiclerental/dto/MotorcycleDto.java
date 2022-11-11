package com.mlazarte.vehiclerental.dto;

import com.mlazarte.vehiclerental.models.Motorcycle;

public class MotorcycleDto {

    private int vehicleId;
    private String description;
    private String brandName;
    private double pricePerHour;
    private double pricePerDay;
    private String registerNumber;
    private int cubicCentimeters;
    private Motorcycle.MotorcycleType type;

    public MotorcycleDto() {
    }

    public MotorcycleDto(int vehicleId, String description, String brandName, double pricePerHour, double pricePerDay,
                         String registerNumber, int cubicCentimeters, Motorcycle.MotorcycleType type) {
        this.vehicleId = vehicleId;
        this.description = description;
        this.brandName = brandName;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.registerNumber = registerNumber;
        this.cubicCentimeters = cubicCentimeters;
        this.type = type;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public int getCubicCentimeters() {
        return cubicCentimeters;
    }

    public void setCubicCentimeters(int cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    public Motorcycle.MotorcycleType getType() {
        return type;
    }

    public void setType(Motorcycle.MotorcycleType type) {
        this.type = type;
    }
}
