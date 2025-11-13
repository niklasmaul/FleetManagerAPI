package de.niklasmaul.fleetmanagerapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private LocalDate date;
    @NotNull
    @Min(0)
    private Double distanceKm;
    @NotNull
    @Min(0)
    private Double fuelUsedLiters;

    @ManyToOne
    @JsonBackReference
    private Vehicle vehicle;

    @Override
    public String toString() {
        return String.format("\nID: %d\nDate: %s\nVehicleID: %d\nDistance in KM: %f\nFuel used in Liters: %f\n", id,date,vehicle.getId(),distanceKm,fuelUsedLiters);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Double getFuelUsedLiters() {
        return fuelUsedLiters;
    }

    public void setFuelUsedLiters(Double fuelUsedLiters) {
        this.fuelUsedLiters = fuelUsedLiters;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
