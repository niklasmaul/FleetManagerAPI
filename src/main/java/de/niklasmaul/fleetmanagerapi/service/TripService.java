package de.niklasmaul.fleetmanagerapi.service;

import de.niklasmaul.fleetmanagerapi.entity.Trip;
import de.niklasmaul.fleetmanagerapi.entity.Vehicle;
import de.niklasmaul.fleetmanagerapi.repository.TripRepository;
import de.niklasmaul.fleetmanagerapi.repository.VehicleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TripService {

    private static final Logger log = LogManager.getLogger(TripService.class);
    private final TripRepository tripRepository;
    private final VehicleRepository vehicleRepository;

    public TripService(TripRepository tripRepository, VehicleRepository vehicleRepository) {
        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
    }

    //=====================================================================================

    public void createTrip(Trip trip, Long vehicleID) {
        log.info("Creating new Trip with VehicleID: {}", vehicleID);
        Vehicle vehicle = vehicleByIdOrThrow(vehicleID);
        trip.setVehicle(vehicle);
        tripRepository.save(trip);
    }

    public List<Trip> getTripsByVehicle(Long vehicleID) {
        log.info("Retrieving list of trips by Vehicle with ID: {}", vehicleID);
        Vehicle vehicle = vehicleByIdOrThrow(vehicleID);
        return vehicle.getTrips();
    }

    //=====================================================================================
    private Vehicle vehicleByIdOrThrow(Long id) {
        return vehicleRepository.findById(id).orElseThrow(()-> {
            log.error("Vehicle not found with ID: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id " + id);
        });
    }

}
