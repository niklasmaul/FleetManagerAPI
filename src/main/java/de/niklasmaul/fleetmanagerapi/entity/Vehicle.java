package de.niklasmaul.fleetmanagerapi.entity;

import de.niklasmaul.fleetmanagerapi.entity.enums.FuelType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotNull
    private Integer year;
    @NotNull
    private Integer mileage;
    @NotNull
    private FuelType fuelType;

    @OneToMany(mappedBy = "vehicle")
    private List<Trip> trips;

    @Override
    public String toString() {
        return String.format("\nID: %d\nBrand: %s\nModel: %s\nYear: %d\nMileage: %d\nFuelType: %s\n", id, brand,model,year,mileage,fuelType);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public List<Trip> getTrips() {
        return trips;
    }
}
