package com.musalasoft.drone.dto;

import com.musalasoft.drone.entity.Drone;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class MedicationDto implements Serializable {
    private final Long medicationId;
    @NotNull
    private final String name;
    @NotNull
    private final String weight;
    @NotNull
    private final String code;
    @NotNull
    private final String image;
//    @NotNull
//    private final Drone droneSerial;
}
