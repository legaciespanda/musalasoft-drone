package com.musalasoft.drone.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class MedicationDto implements Serializable {

    private final String name;
    @NotNull
    private final int weight;
    @NotNull
    private final String code;
    @NotNull
    private final String image;
    @NotNull
    private final String droneSerial;
}
