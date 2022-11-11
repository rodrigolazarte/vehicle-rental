package com.mlazarte.vehiclerental.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RENTALS")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalId;

    private LocalDateTime pickUpDateTime;

    private LocalDateTime returnDateTime;

    private double rentalPrice;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    @Enumerated(EnumType.STRING)
    private RentalType type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rental")
    private List<Vehicle> vehicles = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public enum RentalType {
        HOURLY,
        DAILY,
        FREE
    }

    public enum RentalStatus {
        ACTIVE,
        COMPLETED,
        NOT_RETURNED
    }

    public Rental() {
    }

    public Rental(int rentalId, LocalDateTime pickUpDateTime, LocalDateTime returnDateTime,
                  double rentalPrice, RentalType type, List<Vehicle> vehicles, Client client) {
        this.rentalId = rentalId;
        this.pickUpDateTime = pickUpDateTime;
        this.returnDateTime = returnDateTime;
        this.rentalPrice = rentalPrice;
        this.type = type;
        this.vehicles = vehicles;
        this.client = client;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
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

    public RentalType getType() {
        return type;
    }

    public void setType(RentalType type) {
        this.type = type;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        vehicle.setRental(this);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
        vehicle.setRental(null);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", pickUpDateTime=" + pickUpDateTime +
                ", returnDateTime=" + returnDateTime +
                ", rentalPrice=" + rentalPrice +
                ", type=" + type +
                ", vehicles=" + vehicles +
                ", client=" + client.toString() +
                '}';
    }
}
