package com.service.medecin.repository;

import com.service.medecin.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Integer> {
}
