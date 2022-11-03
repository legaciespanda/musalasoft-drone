package com.musalasoft.drone.service;

import com.musalasoft.drone.dto.DroneDto;
import com.musalasoft.drone.entity.Drone;
import com.musalasoft.drone.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class DroneService {

    @Autowired
    DroneRepository droneRepository;

    public Drone registerDrone(Drone drone){
        return droneRepository.save(drone);
    }

//    @Query("SELECT d FROM DRONE WHERE d.STATE = LOADING")
//    public List<Drone> getLoadingDrones();

    //return drone by the defined state
    public List<Drone> getLoadingDrones(String state){
        return droneRepository.findByState(state);
    }

    //accepts drone serial number and return the battery capacity
    public Optional<Drone> droneBatteryLevel(String droneSerial){
        return droneRepository.findByBatteryCapacity(droneSerial);
    }



}
