package com.domospring.library.controllers;

import com.domospring.library.dao.HistoriqueRepository;
import com.domospring.library.dao.MatereilRepository;
import com.domospring.library.dao.SubscriberRepository;
import com.domospring.library.model.Historique;
import com.domospring.library.model.Materiel;
import com.domospring.library.model.Subscriber;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("")
public class MaterielController {
    @Autowired
    private MatereilRepository mater;
    @Autowired
    private SubscriberRepository sub;
    @Autowired
    private HistoriqueRepository historique;

    //afficher tous les materiels
    @GetMapping(value="/materiels")
    private List<Materiel> getAllMateriels(){
        return mater.findAll();
    }
//Afficher un materiel avec son id
    @GetMapping("/materiels/{id_materiel}")
    Optional<Materiel> GetMateriels(@PathVariable Long id_materiel){
        return mater.findById(id_materiel);
    }

    //creer un materiel
    @PostMapping("/materiels")
    Materiel newMateriel(@RequestBody Materiel newMateriel){return mater.save(newMateriel);}

    //modifier un materiel
    @PutMapping(value = "/materiels/{id_materiel}/subscribers/{id_subscriber}")
    private  Materiel UpdateMateriel(@PathVariable(name = "id_materiel") Long id_materiel, @RequestBody Materiel materiel,@RequestBody Historique newHistorique){
        historique.save(newHistorique);
        materiel.setId_materiel(id_materiel);
        return mater.save(materiel);
    }

    //donner un materiel à un subscriber
    @PostMapping(value = "/materiels/{id_materiel}/subscribers/{id_subscriber}")
    public Materiel updateSubscriberMateriel(
            @PathVariable Long id_materiel,
            @PathVariable Long id_subscriber,
            @RequestBody Historique newHistorique
    ){
        Materiel materiel= mater.findById(id_materiel).get();
        Subscriber subscriber=sub.findById(id_subscriber).get();
        materiel.updateSubscriberMateriel(subscriber);
        historique.save(newHistorique);
        newHistorique.setMateriel(materiel);
        return mater.save(materiel);
    }


    //supprimer un materiel
    @DeleteMapping("/materiels/{id_materiel}")
    void deleteMateriels(@PathVariable Long id_materiel){
        Materiel materiel= mater.findById(id_materiel).get();
      /*  historique.save(newHistorique);
        newHistorique.setMateriel(materiel);  */
        mater.delete(materiel);
    }




}

