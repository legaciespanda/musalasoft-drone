package com.musalasoft.drone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
    @Column(name = "id", nullable = false)
    private Long medicationId;

    @NotBlank(message = "medication name is required")
    private String name;
    @NotBlank(message = "medication weight is required")
    private String weight;
    @NotBlank(message = "medication code is required")
    private String code;
    @NotBlank(message = "medication picture is required")
    private String image;

//    @Column(updatable = false)
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;


    // “many” medications records map to “one” drone record.
    @JoinColumn(name = "droneId")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Drone drone;

}