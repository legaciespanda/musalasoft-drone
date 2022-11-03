package com.musalasoft.drone.controller;

import com.musalasoft.drone.dto.DroneDto;
import com.musalasoft.drone.entity.Drone;
import com.musalasoft.drone.response.ResponseHandler;
import com.musalasoft.drone.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    DroneService droneService;


    //registering a drone
    @RequestMapping(path = "/musalasoft/drone", method = RequestMethod.POST)
    private ResponseEntity<Object> registerDrone(@Valid @RequestBody DroneDto droneDto){

        //drone states must be in uppercase
        String droneState = droneDto.getState().toUpperCase();

        Drone drone = new Drone();
        drone.setSerial(droneDto.getSerial());
        drone.setModel(droneDto.getModel());
        drone.setWeightLimit(droneDto.getWeightLimit());
        drone.setBatteryCapacity(droneDto.getBatteryCapacity());
        drone.setState(droneState);

       LOGGER.info("Drone Registered" + drone);
        try {
            droneService.registerDrone(drone);
            return ResponseHandler.generateResponse("Drone Registered Successfully!", HttpStatus.OK, drone);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //checking available drones for loading;
    @RequestMapping(path = "/musalasoft/drone/loading", method = RequestMethod.GET)
    private ResponseEntity<Object> loadingDrones(){

        List<Drone> drones = new ArrayList<>();

        try {
            //getting all drones with the loading status
                droneService.getLoadingDrones("LOADING").forEach(drones::add);
            return ResponseHandler.generateResponse("Available drones for loading", HttpStatus.OK, drones);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //checking available drones for loading;
    @RequestMapping(path = "/musalasoft/drone/battery-level", method = RequestMethod.GET)
    private ResponseEntity<Object> droneBatteryLevel(@RequestParam(required = false, name = "serial") String serial){
        //checking available drones for loading using their drone serial number
        Optional<Drone> drone = droneService.droneBatteryLevel(serial);

        try {
            //check if drone with serial number exist
//            if(drone.isPresent()){
//                String batteryCapacity = String.valueOf(drone.get().getBatteryCapacity() + "%");
//                return ResponseHandler.generateResponse("Drone battery level", HttpStatus.OK, batteryCapacity);
//            }else {
//                return ResponseHandler.generateResponse("Unable to find drone with serial number or drone does not exist", HttpStatus.NOT_FOUND, null);
//            }

            String batteryCapacity = String.valueOf(drone.get().getBatteryCapacity() + "%");
            return ResponseHandler.generateResponse("Drone battery level", HttpStatus.OK, batteryCapacity);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, drone);
        }
    }

}
