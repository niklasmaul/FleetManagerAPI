package de.niklasmaul.fleetmanagerapi.controller;

import de.niklasmaul.fleetmanagerapi.entity.Vehicle;
import de.niklasmaul.fleetmanagerapi.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Object> postVehicle(@Valid @RequestBody Vehicle vehicle) {
        vehicleService.createVehicle(vehicle);
        return new ResponseEntity<>("Vehicle successful created, " + vehicle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getVehicles() {
        return new ResponseEntity<>(vehicleService.getVehicles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVehicleById(@PathVariable Long id) {
        return new ResponseEntity<>(vehicleService.getVehicleById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        vehicleService.updateVehicle(id, vehicle);
        return new ResponseEntity<>("Vehicle successful updated" + vehicleService.getVehicleById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>("Vehicle successful deleted" + vehicle, HttpStatus.OK);
    }

}
