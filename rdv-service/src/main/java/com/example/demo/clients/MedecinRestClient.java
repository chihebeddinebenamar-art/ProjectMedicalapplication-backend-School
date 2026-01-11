package com.example.demo.clients;

import com.example.demo.Model.Medecin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "MEDECIN-SERVICE")
public interface MedecinRestClient {
    @GetMapping("/api/medecin/{id}")
    Optional<Medecin> getMedecinById(@PathVariable int id);
}

