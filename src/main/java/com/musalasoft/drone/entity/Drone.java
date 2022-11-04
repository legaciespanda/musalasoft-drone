package com.musalasoft.drone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "drone")
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "drone_id", nullable = false)
    private Long droneId;

    @Min(value = 100) //drone serial must not be more than 100 charcters
    @NotNull(message = "Drone serial number is required")
    @Column(name = "serial", nullable = false)
    private String serial;

    @NotNull(message = "Model is required")
    @Column(name = "model", nullable = false)
    private String model;

    @NotNull(message = "Weight limit is required")
    @Column(name = "weight_limit", nullable = false)
    private int weightLimit;

    @NotNull(message = "Battery capacity is required")
    @Column(name = "battery_capacity", nullable = false)
    private int batteryCapacity;

    @Column(name = "state", nullable = false)
    @NotNull(message = "State is required")
    private String state;

//    @OneToMany(mappedBy = "drone")
//    private List<Medication> medications;

}