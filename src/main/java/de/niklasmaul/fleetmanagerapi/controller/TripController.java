package de.niklasmaul.fleetmanagerapi.controller;

import de.niklasmaul.fleetmanagerapi.entity.Trip;
import de.niklasmaul.fleetmanagerapi.service.TripService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<Object> postTrip(@Valid @RequestBody Trip trip, @RequestParam Long vehicleID) {
        tripService.createTrip(trip, vehicleID);
        return new ResponseEntity<>("Trip successful created: " + trip, HttpStatus.CREATED);
    }

    @GetMapping("/vehicle/{vehicleID}")
    public ResponseEntity<Object> getTripsByVehicle(@PathVariable Long vehicleID) {
        return new ResponseEntity<>(tripService.getTripsByVehicle(vehicleID), HttpStatus.OK);
    }

}
