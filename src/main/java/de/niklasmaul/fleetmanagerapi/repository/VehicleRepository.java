package de.niklasmaul.fleetmanagerapi.repository;

import de.niklasmaul.fleetmanagerapi.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
