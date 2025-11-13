package de.niklasmaul.fleetmanagerapi.service;

import de.niklasmaul.fleetmanagerapi.entity.Vehicle;
import de.niklasmaul.fleetmanagerapi.repository.VehicleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VehicleService {

    private static final Logger log = LogManager.getLogger(VehicleService.class);
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    public void createVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        log.info("Creating a new Vehicle with ID {}", vehicle.getId());
    }

    public List<Vehicle> getVehicles() {
        log.info("Retrieving all Vehicles");
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        log.info("Retrieving Vehicle with id {}", id);
        return vehicleByIdOrThrow(id);
    }

    public void updateVehicle(Long id, Vehicle vehicleDetails) {
        log.info("Update Vehicle with ID {}", id);
        Vehicle vehicle = vehicleByIdOrThrow(id);
        if(vehicleDetails.getBrand() != null)vehicle.setBrand(vehicleDetails.getBrand());
        if(vehicleDetails.getModel() != null)vehicle.setModel(vehicleDetails.getModel());
        if(vehicleDetails.getYear() != null)vehicle.setYear(vehicleDetails.getYear());
        if(vehicleDetails.getMileage() != null)vehicle.setMileage(vehicleDetails.getMileage());
        if(vehicleDetails.getFuelType() != null)vehicle.setFuelType(vehicleDetails.getFuelType());
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        log.info("Deleting Vehicle with ID {}", id);
        Vehicle vehicle = vehicleByIdOrThrow(id);
        vehicleRepository.delete(vehicle);
    }

    private Vehicle vehicleByIdOrThrow(Long id) {
        return vehicleRepository.findById(id).orElseThrow(()-> {
            log.error("Vehicle not found with ID: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id " + id);
        });
    }

}
