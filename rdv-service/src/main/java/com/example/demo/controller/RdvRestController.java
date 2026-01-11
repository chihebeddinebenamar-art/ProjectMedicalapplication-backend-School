package com.example.demo.controller;


import com.example.demo.Model.Medecin;
import com.example.demo.Model.Patient;
import com.example.demo.clients.MedecinRestClient;
import com.example.demo.clients.PatientRestClient;
import com.example.demo.entities.Rdv;
import com.example.demo.service.IServiceRdv;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rdv")
public class RdvRestController {

    private final IServiceRdv serviceRdv;
    private final MedecinRestClient medecinRestClient;
    private final PatientRestClient patientRestClient;

    public RdvRestController(IServiceRdv serviceRdv, MedecinRestClient medecinRestClient, PatientRestClient patientRestClient) {
        this.serviceRdv = serviceRdv;
        this.medecinRestClient = medecinRestClient;
        this.patientRestClient = patientRestClient;
    }

    @GetMapping("{id}")
    public Rdv find(@PathVariable int id) {
        Rdv rdv = serviceRdv.getRdvById(id);
        Optional<Patient> patient = patientRestClient.getPatientById(rdv.getPatientId());
        Optional<Medecin> medecin = medecinRestClient.getMedecinById(rdv.getMedecinId());
        if(patient.isPresent() && medecin.isPresent()){
            rdv.setPatient(patient.get());
            rdv.setMedecin(medecin.get());
        }
        return rdv;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addRdv(@RequestBody Rdv rdv) {
        Rdv rdv1 = null;
        Optional<Patient> patient = patientRestClient.getPatientById(rdv.getPatientId());
        Optional<Medecin> medecin = medecinRestClient.getMedecinById(rdv.getMedecinId());
        if(patient.isPresent() && medecin.isPresent()){
            rdv1 = serviceRdv.addRdv(rdv);
        }
        if(rdv1 != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(rdv1);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le Rdv ne peut pas être crée, merci de vérifier vos données");
        }
    }

    @GetMapping("/all")
    public List<Rdv> getAllRdvs() {
        List<Rdv> rdvs = serviceRdv.getAll();
        for (Rdv rdv : rdvs) {
            Optional<Patient> patient = patientRestClient.getPatientById(rdv.getPatientId());
            Optional<Medecin> medecin = medecinRestClient.getMedecinById(rdv.getMedecinId());
            if(patient.isPresent() && medecin.isPresent()){
                rdv.setPatient(patient.get());
                rdv.setMedecin(medecin.get());
            }
        }
        return rdvs;
    }
}
