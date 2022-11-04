package com.musalasoft.drone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medication_id", nullable = false)
    private Long medicationId;

    @NotBlank(message = "medication name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "medication weight is required")
    @Column(name = "weight", nullable = false)
    private int weight;

    @NotBlank(message = "medication code is required")
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "image", nullable = false)
    @NotBlank(message = "medication picture is required")
    private String image;

    @Column(name = "drone_serial", nullable = false)
    @NotBlank(message = "drone serial number is required")
    private String droneSerial;


}