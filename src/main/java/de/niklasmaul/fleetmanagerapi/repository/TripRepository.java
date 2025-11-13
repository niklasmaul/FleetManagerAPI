package de.niklasmaul.fleetmanagerapi.repository;

import de.niklasmaul.fleetmanagerapi.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}
