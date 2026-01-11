package com.service.medecin.service;

import com.service.medecin.entities.Medecin;

import java.util.List;
import java.util.Optional;

public interface IServiceMedecin {

    public Medecin addMedecin(Medecin medecin);
    public List<Medecin> getAllMedecins();
    public Optional<Medecin> getMedecinById(int id);
}
