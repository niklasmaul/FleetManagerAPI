package de.niklasmaul.fleetmanagerapi.repository;

import de.niklasmaul.fleetmanagerapi.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
