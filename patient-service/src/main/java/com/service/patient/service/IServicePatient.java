package com.service.patient.service;

import com.service.patient.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface IServicePatient {

    public Patient addPatient(Patient patient);
    public List<Patient> getAllPatients();
    public Optional<Patient> getPatientById(int id);
}
