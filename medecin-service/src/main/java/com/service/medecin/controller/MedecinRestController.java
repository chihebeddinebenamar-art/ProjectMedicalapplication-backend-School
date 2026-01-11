package com.service.medecin.controller;

import com.service.medecin.entities.Medecin;
import com.service.medecin.service.IServiceMedecin;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/medecin/")
public class MedecinRestController {
    IServiceMedecin iServiceMedecin;

    @GetMapping("{id}")
    public Optional<Medecin> getById(@PathVariable int id){
        return iServiceMedecin.getMedecinById(id);
    }

    @PostMapping("add")
    public Medecin add(@RequestBody Medecin medecin){
        return iServiceMedecin.addMedecin(medecin);
    }

    @GetMapping("all")
    public List<Medecin> all(){
        return iServiceMedecin.getAllMedecins();
    }
}
