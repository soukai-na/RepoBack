package com.domospring.library.controllers;


import com.domospring.library.dao.FactureRepository;
import com.domospring.library.dao.SubscriberRepository;
import com.domospring.library.model.Facture;
import com.domospring.library.model.Subscriber;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("")
@Slf4j
public class FactureController {
    @Autowired
    private FactureRepository fact;
    @Autowired
    private SubscriberRepository sub;

    //afficher tous les factures
    @GetMapping(value="/factures")
    private ResponseEntity<List<Facture>> getAllFactures(){
        try{
            List<Facture> listeFacture=fact.findAll();
            log.info("factures importés avec success");
            return new ResponseEntity(listeFacture, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur importation des factures");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //afficher une facture par son id
    @GetMapping("/factures/{id_facture}")
    ResponseEntity<Optional<Facture>> GetFacture(@PathVariable Long id_facture){
    try{
        Optional<Facture> maFacture=fact.findById(id_facture);
        log.info("la facture est importé");
        return new ResponseEntity(maFacture,HttpStatus.OK);
    } catch(Exception e){
        log.error("erreur importation de la facture");
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }

    //affecter la facture à un subscriber
    @PostMapping(value="/subscribers/{id_subscriber}/factures")
    public ResponseEntity<Facture> newFactureSubscriber(
            @PathVariable Long id_subscriber,
            @RequestBody Facture newFacture){
        try{
            sub.findById(id_subscriber).map(subscriber ->
            {
                newFacture.setSubscriber(subscriber);
                return fact.save(newFacture);
            });
            log.info("la facture est ajouté");
            return new ResponseEntity(HttpStatus.OK);

        }catch(Exception e){
            log.error("erreur importation de la facture");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


 //Supprimer la facture d'un subscriber
    @DeleteMapping("subscribers/{id_subscriber}/factures/{id_facture}")
    public ResponseEntity<String> deleteFacture(@PathVariable Long id_facture,@PathVariable Long id_subscriber){
        try{
            if(!sub.existsById(id_subscriber)){
                log.error("subscriber not found");
            }
            fact.findById(id_facture).map(
                    facture->{
                        fact.delete(facture);
                        log.info("la facture est supprimé");
                        return new ResponseEntity(HttpStatus.OK);
                    }
            );
            log.info("la facture est supprimé");
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            log.error("erreur suppresion de la facture");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}

