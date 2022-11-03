package com.musalasoft.drone.repository;

import com.musalasoft.drone.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    Optional<Drone> findByBatteryCapacity(String droneSerial);

    List<Drone> findByState(String state);
}