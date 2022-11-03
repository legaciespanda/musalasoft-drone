package com.musalasoft.drone.controller;

import com.musalasoft.drone.dto.DroneDto;
import com.musalasoft.drone.entity.Drone;
import com.musalasoft.drone.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ApiController {

    private final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    DroneService droneService;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/musalasoft/drone", method = RequestMethod.POST)
    private ResponseEntity<String> registerDrone(@Valid @RequestBody DroneDto droneDto){

        //drone states must be in uppercase
        String droneState = droneDto.getState().toUpperCase();

        Drone drone = new Drone();
        drone.setSerial(droneDto.getSerial());
        drone.setModel(droneDto.getModel());
        drone.setWeightLimit(droneDto.getWeightLimit());
        drone.setBatteryCapacity(droneDto.getBatteryCapacity());
        drone.setState(droneState);

       LOGGER.info("Drone Resgistered" + drone);
        try {
            droneService.registerDrone(drone);
            return new ResponseEntity<>("Drone Registered \n "+drone, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
