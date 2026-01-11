package com.example.demo.repository;

import com.example.demo.entities.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RdvRepository extends JpaRepository<Rdv, Integer> {
    // 🔹 Application 1 :
    // Vérifie si un patient a déjà un RDV à une date et heure données
    Optional<Rdv> findByPatient_IdAndDateRdv(int patientId, LocalDateTime dateRdv);

    // Vérifie si un médecin a déjà un RDV à une date et heure données
    Optional<Rdv> findByMedecin_IdAndDateRdv(int medecinId, LocalDateTime dateRdv);

    // 🔹 Application 2 :
    // Retourner la liste des RDV ordonnée par date croissante
    List<Rdv> findAllByOrderByDateRdvAsc();

    // 🔹 Application 3 :
    // Rechercher les RDV d’un médecin pour une date donnée (ignore l’heure)
    List<Rdv> findByMedecin_IdAndDateRdvBetween(int medecinId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
