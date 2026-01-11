package com.service.patient.service;

import com.service.patient.entities.Patient;
import com.service.patient.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ServicePatient implements IServicePatient{

    PatientRepository patientRepository;
    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(int id){
        return patientRepository.findById(id);
    }
}
