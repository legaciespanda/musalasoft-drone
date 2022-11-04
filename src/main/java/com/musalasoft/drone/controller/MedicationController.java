package com.musalasoft.drone.controller;


import com.musalasoft.drone.dto.MedicationDto;
import com.musalasoft.drone.entity.Drone;
import com.musalasoft.drone.entity.Medication;
import com.musalasoft.drone.response.ResponseHandler;
import com.musalasoft.drone.service.DroneService;
import com.musalasoft.drone.service.MedicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class MedicationController {

    private final Logger LOGGER = LoggerFactory.getLogger(MedicationController.class);

    @Autowired
    MedicationService medicationService;

    @Autowired
    DroneService droneService;


    //registering a drone
    @RequestMapping(path = "/musalasoft/medication", method = RequestMethod.POST)
    private ResponseEntity<Object> loadMedication(@Valid MedicationDto medicationDto){

        Optional<Drone> drone = droneService.getDroneData(medicationDto.getDroneSerial());
       LOGGER.info("Medication loaded info" + medicationDto);

        try {

            //check if drone with serial number exist
            if(!drone.isPresent()){
                return ResponseHandler.generateResponse("Can't load medication as drone with serial number - " + medicationDto.getDroneSerial() + " does not exist", HttpStatus.FORBIDDEN, null);
            }

            //Prevent the drone from being loaded with more weight that it can carry;
            if(medicationDto.getWeight() > drone.get().getWeightLimit()){
                return ResponseHandler.generateResponse("The drone can't carry more weight than itself", HttpStatus.FORBIDDEN, null);
            }

            //Prevent the drone from being in LOADING state if the battery level is **below 25%**;
            if(drone.get().getBatteryCapacity() <= 25){
                return ResponseHandler.generateResponse("You can't load medical items. Drone bettery level is below 25%", HttpStatus.FORBIDDEN, null);
            }

            // name (allowed only letters, numbers, ‘-‘, ‘_’);
            Pattern pattern =  Pattern.compile("[a-zA-Z0-9-_]*");
            if(!pattern.matcher(medicationDto.getCode()).matches()){
                return ResponseHandler.generateResponse("medication name allowed only letters, numbers, ‘-‘, ‘_’", HttpStatus.FORBIDDEN, null);
            }

           // (allow medication code only upper case letters, underscore and numbers)
            Pattern pattern1 =  Pattern.compile("[A-Z0-9_]*");
            if(!pattern1.matcher(medicationDto.getCode()).matches()){
                return ResponseHandler.generateResponse("medication code only upper case letters, underscore and numbers", HttpStatus.FORBIDDEN, null);
            }


            //save medical items
            Medication medication = new Medication();
            medication.setName(medicationDto.getName());
            medication.setWeight(medicationDto.getWeight());
            medication.setCode(medicationDto.getCode());
            medication.setImage(medicationDto.getImage());

            //map the drone to the medication
//        Drone droneMap = drone.get();
//        medication.setDrone(droneMap);
            medication.setDroneSerial(medicationDto.getDroneSerial());

            //save
            medicationService.loadMedication(medication);

            LOGGER.info("Medication loaded for drone with serial " + medicationDto.getDroneSerial());

            return ResponseHandler.generateResponse("Medication loaded for drone with serial " + medicationDto.getDroneSerial(), HttpStatus.OK, medication);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

   // checking loaded medication items for a given drone;
}
