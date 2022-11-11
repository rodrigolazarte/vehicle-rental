package com.mlazarte.vehiclerental.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "MOTORCYCLES")
public class Motorcycle extends Vehicle {

    private String registerNumber;
    private int cubicCentimeters;

    @Enumerated(EnumType.STRING)
    private MotorcycleType type;

    public enum MotorcycleType {
        STANDARD,
        CRUISER,
        SPORT,
        TOURING,
        SCOOTER
    }

    public Motorcycle() {
    }

    public Motorcycle(String registerNumber, int cubicCentimeters, MotorcycleType type) {
        this.registerNumber = registerNumber;
        this.cubicCentimeters = cubicCentimeters;
        this.type = type;
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

    public MotorcycleType getType() {
        return type;
    }

    public void setType(MotorcycleType type) {
        this.type = type;
    }
}

