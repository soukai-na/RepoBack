package com.domospring.library.controllers;

import com.domospring.library.dao.HistoriqueRepository;
import com.domospring.library.dao.MatereilRepository;
import com.domospring.library.dao.SubscriberRepository;
import com.domospring.library.model.Historique;
import com.domospring.library.model.Materiel;
import com.domospring.library.model.Subscriber;
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
public class MaterielController {
    @Autowired
    private MatereilRepository mater;
    @Autowired
    private SubscriberRepository sub;
    @Autowired
    private HistoriqueRepository historique;

    //afficher tous les materiels
    @GetMapping(value="/materiels")
    private ResponseEntity<List<Materiel>> getAllMateriels(){
        try{
            List<Materiel> listeMateriel=mater.findAll();
            log.info("materiels importés avec success");
            return new ResponseEntity(listeMateriel, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur importation des materiels");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Afficher un materiel par son id
    @GetMapping("/materiels/{id_materiel}")
    private ResponseEntity<Optional<Materiel>> GetMateriels(@PathVariable Long id_materiel){
        try{
            Optional<Materiel> UnMateriel=mater.findById(id_materiel);
            log.info("materiel importé avec success");
            return new ResponseEntity(UnMateriel, HttpStatus.OK);
        }catch(Exception e){
            log.error("erreur importation du materiel");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //créer un materiel
    @PostMapping("/materiels")
    private ResponseEntity<Materiel> newMateriel(@RequestBody Materiel newMateriel){
        try{
            Materiel createMateriel=mater.save(newMateriel);
            log.info("materiel créée avec success");
            return new ResponseEntity(createMateriel, HttpStatus.OK);
        }catch(Exception e){
            log.error("erreur d'ajout du'un materiel");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //modifier un materiel qui affecté à aucun subscriber
    @PutMapping(value = "/materiels/{id_materiel}")
    private  ResponseEntity<Materiel> UpdateMateriel(@PathVariable(name = "id_materiel") Long id_materiel, @RequestBody Materiel materiel,@RequestBody Historique newHistorique){
        try{
            historique.save(newHistorique);
            materiel.setId_materiel(id_materiel);
            Materiel putMateriel= mater.save(materiel);
            log.info("materiel modifié avec success");
            return new ResponseEntity(putMateriel, HttpStatus.OK);

        }catch(Exception e){
            log.error("erreur dans la modification du'un materiel");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //affecter un materiel à un subscriber
    @PostMapping(value = "/materiels/{id_materiel}/subscribers/{id_subscriber}")
    public ResponseEntity<Materiel> updateSubscriberMateriel(
            @PathVariable Long id_materiel,
            @PathVariable Long id_subscriber,
            @RequestBody Historique newHistorique
    ){
        try{
            Materiel materiel= mater.findById(id_materiel).get();
            Subscriber subscriber=sub.findById(id_subscriber).get();
            materiel.updateSubscriberMateriel(subscriber);
            historique.save(newHistorique);
            newHistorique.setMateriel(materiel);
            Materiel matSub= mater.save(materiel);
            log.info("materiel affecté à un subscriber avec success");
            return new ResponseEntity(matSub, HttpStatus.OK);
        }catch(Exception e){
            log.error("erreur dans la modification du'un materiel");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //supprimer un materiel
    @DeleteMapping("/materiels/{id_materiel}")
    public  ResponseEntity<?>  deleteMateriels(@PathVariable Long id_materiel){
        try{
            Materiel materiel= mater.findById(id_materiel).get();
            mater.delete(materiel);
            log.info("materiel supprimé");
            return new ResponseEntity<String>( HttpStatus.OK);
        }catch(Exception e){
            log.error("erreur de suppresion du materiel");
            return   new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}

