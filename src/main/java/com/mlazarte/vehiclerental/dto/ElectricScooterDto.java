package com.mlazarte.vehiclerental.dto;

public class ElectricScooterDto {
    private int vehicleId;
    private String description;
    private String brandName;
    private double pricePerHour;
    private double pricePerDay;
    private double maxWeight;
    private int motorSize;
    private int batteryCapacity;

    public ElectricScooterDto() {
    }

    public ElectricScooterDto(int vehicleId, String description, String brandName, double pricePerHour,
                              double pricePerDay, double maxWeight, int motorSize, int batteryCapacity) {
        this.vehicleId = vehicleId;
        this.description = description;
        this.brandName = brandName;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.maxWeight = maxWeight;
        this.motorSize = motorSize;
        this.batteryCapacity = batteryCapacity;
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

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMotorSize() {
        return motorSize;
    }

    public void setMotorSize(int motorSize) {
        this.motorSize = motorSize;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
