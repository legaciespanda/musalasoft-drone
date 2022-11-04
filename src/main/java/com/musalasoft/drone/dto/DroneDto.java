package com.musalasoft.drone.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class DroneDto implements Serializable {

    @NotNull
    @Min(value = 100) //drone serial must not be more than 100 charcters
    private final String serial;
    @NotNull
    private final String model;
    @NotNull
    private final int weightLimit;
    @NotNull
    private final int batteryCapacity;
    @NotNull
    private final String state;
}
