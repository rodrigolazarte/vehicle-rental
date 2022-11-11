package com.mlazarte.vehiclerental.dto;

import com.mlazarte.vehiclerental.models.Rental;

import java.time.LocalDateTime;

public class RentalDto {
    private LocalDateTime pickUpDateTime;

    private LocalDateTime returnDateTime;

    private double rentalPrice;

    private Rental.RentalType type;

    private int[] vehicleIds;

    private int clientId;

    public RentalDto() {
    }

    public RentalDto(LocalDateTime pickUpDateTime, LocalDateTime returnDateTime,
                     double rentalPrice, Rental.RentalType type, int[] vehicleIds, int clientId) {
        this.pickUpDateTime = pickUpDateTime;
        this.returnDateTime = returnDateTime;
        this.rentalPrice = rentalPrice;
        this.type = type;
        this.vehicleIds = vehicleIds;
        this.clientId = clientId;
    }

    public LocalDateTime getPickUpDateTime() {
        return pickUpDateTime;
    }

    public void setPickUpDateTime(LocalDateTime pickUpDateTime) {
        this.pickUpDateTime = pickUpDateTime;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public Rental.RentalType getType() {
        return type;
    }

    public void setType(Rental.RentalType type) {
        this.type = type;
    }

    public int[] getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(int[] vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
