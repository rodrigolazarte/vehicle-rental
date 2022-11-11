package com.mlazarte.vehiclerental.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ELECTRIC_SCOOTERS")
public class ElectricScooter extends Vehicle {

    private double maxWeight;
    private int motorSize;
    private int batteryCapacity;

    public ElectricScooter() {
    }

    public ElectricScooter(double maxWeight, int motorSize, int batteryCapacity) {
        this.maxWeight = maxWeight;
        this.motorSize = motorSize;
        this.batteryCapacity = batteryCapacity;
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
