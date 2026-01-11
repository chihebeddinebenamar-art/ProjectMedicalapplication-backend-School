package com.example.demo.service;



import com.example.demo.entities.Rdv;

import java.time.LocalDate;
import java.util.List;

public interface IServiceRdv {

    Rdv getRdvById(int id);
    Rdv addRdv(Rdv rdv);

    /**
     * Retourne la liste de tous les RDV triés par date et heure croissante
     */
    List<Rdv> getAll();

    /**
     * Retourne la liste des RDV d’un médecin pour une date donnée
     * (la date sera envoyée depuis le contrôleur en paramètre)
     */
    List<Rdv> getRdvByMedecinAndDate(int medecinId, LocalDate date);
}
