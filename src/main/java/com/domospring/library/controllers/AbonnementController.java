package com.domospring.library.controllers;

import com.domospring.library.dao.AbonnementRepository;
import com.domospring.library.model.Abonnement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("")
public class AbonnementController {
    @Autowired
    private AbonnementRepository abonnement;

    //afficher une abonnement
    @GetMapping(value="/abonnements")
    private List<Abonnement> getAllAbonnements(){
        return abonnement.findAll();
    }

    //afficher une abonnement par son id
    @GetMapping("/abonnements/{id_abonnement}")
    Optional<Abonnement> GetAbonnement(@PathVariable Long id_abonnement){
        return abonnement.findById(id_abonnement);
    }

    //creer une abonnement
    @PostMapping("/abonnements")
    Abonnement newAbonnement(@RequestBody Abonnement newAbonnement){ return abonnement.save(newAbonnement);}

    //modifier une abonnement
    @PutMapping(value = "/abonnements/{id_abonnement}")
    private Abonnement UpdateAbonnement(@PathVariable(name = "id_abonnement") Long id_abonnement, @RequestBody Abonnement abn){
        abn.setId_abonnement(id_abonnement);
        return abonnement.save(abn);
    }

    //supprimer une abonnement
    @DeleteMapping("/abonnements/{id_abonnement}")
    void deleteAbonnements(@PathVariable Long id_abonnement) {
        abonnement.deleteById(id_abonnement);
    }

}

