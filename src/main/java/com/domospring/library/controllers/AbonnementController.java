package com.domospring.library.controllers;

import com.domospring.library.dao.AbonnementRepository;
import com.domospring.library.model.Abonnement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("")
@Slf4j
public class AbonnementController {
    @Autowired
    private AbonnementRepository abonnement;

    //afficher tous les abonnements
    @GetMapping(value="/abonnements")
    private ResponseEntity<List<Abonnement>> getAllAbonnements(){
        try{
            List<Abonnement> listeAbonnement=abonnement.findAll();
            log.info("abonnements importés avec success");
            return new ResponseEntity(listeAbonnement, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur importation des abonnements");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //afficher une abonnement par son id
    @GetMapping("/abonnements/{id_abonnement}")
    private ResponseEntity<Optional<Abonnement>> GetAbonnement(@PathVariable Long id_abonnement){
        try{
            Optional<Abonnement>  UneAbonnement=abonnement.findById(id_abonnement);
            log.info("l'abonnement importé avec success");
            return new ResponseEntity(UneAbonnement, HttpStatus.OK);

        }catch(Exception e){
            log.error("erreur importation d'abonnement");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //créer une abonnement
    @PostMapping("/abonnements")
    private ResponseEntity<Abonnement> newAbonnement(@RequestBody Abonnement newAbonnement){
        try{
            Abonnement createAbonnement=abonnement.save(newAbonnement);
            log.info("Abonnement créée avec success");
            return new ResponseEntity(createAbonnement,HttpStatus.OK);
        }catch(Exception e){
            log.error("erreur de création d'abonnement");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //modifier une abonnement
    @PutMapping(value = "/abonnements/{id_abonnement}")
    private ResponseEntity<Abonnement> UpdateAbonnement(@PathVariable(name = "id_abonnement") Long id_abonnement, @RequestBody Abonnement abn){
        try{
            abn.setId_abonnement(id_abonnement);
            Abonnement  updateAbonnement=abonnement.save(abn);
            log.info("abonnement modifié");
            return new ResponseEntity(updateAbonnement,HttpStatus.OK);
        }catch(Exception e){
            log.error("erreur de modification d'abonnement");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //supprimer une abonnement
    @DeleteMapping("/abonnements/{id_abonnement}")
    public  ResponseEntity<?>  deleteAbonnements(@PathVariable Long id_abonnement) {
        try{
            abonnement.deleteById(id_abonnement);
            log.info("abonnement supprimé");
           return new ResponseEntity<String>( HttpStatus.OK);
        }catch(Exception e){
            log.error("erreur de suppresion d'abonnement");
           return   new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

