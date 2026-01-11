package com.example.demo.service;

import com.example.demo.entities.Rdv;
import com.example.demo.repository.RdvRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceRdv implements IServiceRdv {

    private final RdvRepository rdvRepository;

    public ServiceRdv(RdvRepository rdvRepository) {
        this.rdvRepository = rdvRepository;
    }

    @Override
    public Rdv getRdvById(int id){
        return rdvRepository.findById(id).get();
    }

    @Override
    public Rdv addRdv(Rdv rdv) {
        // Vérifier la disponibilité du patient à cette date
        Optional<Rdv> existingPatientRdv = rdvRepository.findByPatient_IdAndDateRdv(rdv.getPatientId(), rdv.getDateRdv());
        if (existingPatientRdv.isPresent()) {
            throw new RuntimeException("❌ Ce patient a déjà un rendez-vous à cette date et heure !");
        }

        // Vérifier la disponibilité du médecin à cette date
        Optional<Rdv> existingMedecinRdv = rdvRepository.findByMedecin_IdAndDateRdv(rdv.getMedecinId(), rdv.getDateRdv());
        if (existingMedecinRdv.isPresent()) {
            throw new RuntimeException("❌ Ce médecin a déjà un rendez-vous à cette date et heure !");
        }

        // Sinon, enregistrer le rendez-vous
        return rdvRepository.save(rdv);
    }

    @Override
    public List<Rdv> getAll() {
        // 🔹 Application 2 : retourne trié par date croissante
        return rdvRepository.findAllByOrderByDateRdvAsc();
    }

    // 🔹 Application 3 : liste des rdv d’un médecin à une date donnée
    public List<Rdv> getRdvByMedecinAndDate(int medecinId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return rdvRepository.findByMedecin_IdAndDateRdvBetween(medecinId, startOfDay, endOfDay);
    }
}
