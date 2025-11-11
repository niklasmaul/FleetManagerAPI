package de.niklasmaul.fleetmanagerapi.service;

import de.niklasmaul.fleetmanagerapi.entity.Vehicle;
import de.niklasmaul.fleetmanagerapi.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    public void createVehicle(Vehicle vehicle) {
        if(vehicle.getBrand() == null || vehicle.getModel() == null || vehicle.getYear() == null || vehicle.getFuelType() == null || vehicle.getMileage() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some content is missing");
        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id " + id));
    }

    public void updateVehicle(Long id, Vehicle vehicleDetails) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id " + id));
        vehicle.setBrand(vehicleDetails.getBrand());
        vehicle.setModel(vehicleDetails.getModel());
        vehicle.setYear(vehicleDetails.getYear());
        vehicle.setMileage(vehicleDetails.getMileage());
        vehicle.setFuelType(vehicleDetails.getFuelType());
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id " + id));
        vehicleRepository.delete(vehicle);
    }

}
