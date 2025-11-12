package de.niklasmaul.fleetmanagerapi.service;

import de.niklasmaul.fleetmanagerapi.entity.Vehicle;
import de.niklasmaul.fleetmanagerapi.repository.VehicleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VehicleService {

    private static final Logger logger = LogManager.getLogger(VehicleService.class);

    @Autowired
    private VehicleRepository vehicleRepository;


    public void createVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        logger.info("Creating a new Vehicle with ID {}", vehicle.getId());
    }

    public List<Vehicle> getVehicles() {
        logger.info("Retrieving all Vehicles");
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        logger.info("Retrieving Vehicle with id {}", id);
        return vehicleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id " + id));
    }

    public void updateVehicle(Long id, Vehicle vehicleDetails) {
        logger.info("Update Vehicle with ID {}", id);
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id " + id));
        if(vehicleDetails.getBrand() != null)vehicle.setBrand(vehicleDetails.getBrand());
        if(vehicleDetails.getModel() != null)vehicle.setModel(vehicleDetails.getModel());
        if(vehicleDetails.getYear() != null)vehicle.setYear(vehicleDetails.getYear());
        if(vehicleDetails.getMileage() != null)vehicle.setMileage(vehicleDetails.getMileage());
        if(vehicleDetails.getFuelType() != null)vehicle.setFuelType(vehicleDetails.getFuelType());
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        logger.info("Deleting Vehicle with ID {}", id);
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id " + id));
        vehicleRepository.delete(vehicle);
    }

}
