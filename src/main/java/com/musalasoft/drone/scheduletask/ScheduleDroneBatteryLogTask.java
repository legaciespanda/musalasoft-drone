package com.musalasoft.drone.scheduletask;


import com.musalasoft.drone.entity.Drone;
import com.musalasoft.drone.service.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ScheduleDroneBatteryLogTask {
    @Autowired
    DroneService droneService;

    @Scheduled(fixedRate = 5000) //5 seconds
    public void execute() throws InterruptedException{
        log.info("Drone Battery ....");

        List<Drone> droneList = droneService.getAllDrones();

//        log.info("Drone ID.......... " + droneList.stream().map(drone -> drone.getDroneId()));
//        log.info("Drone Serial.......... " + droneList.stream().map(drone -> drone.getSerial()));

        droneList.forEach(
                drone -> log.info("Drone ID.........." + drone.getDroneId())
        );
        droneList.forEach(
                drone -> log.info("Drone Serial.........." + drone.getSerial())
        );
        droneList.forEach(
                drone -> log.info("Drone Battery Level.........." + drone.getBatteryCapacity())
        );

    }
}
