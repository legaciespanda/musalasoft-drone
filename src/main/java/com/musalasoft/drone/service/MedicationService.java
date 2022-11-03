package com.musalasoft.drone.service;

import com.musalasoft.drone.entity.Medication;
import com.musalasoft.drone.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {

    @Autowired
    MedicationRepository medicationRepository;

    public Medication loadMedication(Medication medication){
        return medicationRepository.save(medication);
    }
}
