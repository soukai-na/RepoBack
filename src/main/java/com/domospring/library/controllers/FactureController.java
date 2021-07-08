package com.domospring.library.controllers;

import com.domospring.library.dao.AbonnementRepository;
import com.domospring.library.dao.FactureRepository;
import com.domospring.library.dao.SubscriberRepository;
import com.domospring.library.model.Facture;
import com.domospring.library.model.Subscriber;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("")
public class FactureController {
    @Autowired
    private FactureRepository fact;
    @Autowired
    private SubscriberRepository sub;

    //afficher tous les factures
    @GetMapping(value="/factures")
    private List<Facture> getAllFactures(){
        return fact.findAll();
    }

    //afficher la facture par son id
    @GetMapping("/factures/{id_facture}")
    Optional<Facture> GetFacture(@PathVariable Long id_facture){
        return fact.findById(id_facture);
    }

    //donner la facture à un subscriber
   @PutMapping(value="/subscribers/{id_subscriber}/factures/{id_facture}")
    public Facture updateFactureSubscriber(
            @PathVariable Long id_facture,
            @PathVariable Long id_subscriber
    ){
        Facture facture=fact.findById(id_facture).get();
        Subscriber subscriber=sub.findById(id_subscriber).get();
        facture.updateFactureSubscriber(subscriber);
        return fact.save(facture);
    }


    //modifier une facture
    @PostMapping(value = "/factures")
    public Facture UpdateFacture(@RequestBody Facture factureUpdated) {
        return fact.save(factureUpdated);
    }

    //delete facture
    @DeleteMapping("/factures/{id_facture}")
    public void deleteFactures(@PathVariable Long id_facture)  {
        fact.deleteById(id_facture);
    }


}

