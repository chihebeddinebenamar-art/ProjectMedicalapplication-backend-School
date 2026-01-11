package com.example.demo.clients;

import com.example.demo.Model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "PATIENT-SERVICE")
public interface PatientRestClient {
    @GetMapping("/api/patient/{id}")
    Optional<Patient> getPatientById(@PathVariable int id);
}

