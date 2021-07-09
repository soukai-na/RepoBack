package com.domospring.library.controllers;

import com.domospring.library.dao.MatereilRepository;
import com.domospring.library.dao.SubscriberRepository;
import com.domospring.library.model.Materiel;
import com.domospring.library.model.Subscriber;
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
    @PutMapping(value = "/materiels/{id_materiel}")
    private  Materiel UpdateMateriel(@PathVariable(name = "id_materiel") Long id_materiel, @RequestBody Materiel mtl){
        mtl.setId_materiel(id_materiel);
        return mater.save(mtl);
    }

    //donner un materiel à un subscriber
    @PostMapping(value = "/materiels/{id_materiel}/subscribers/{id_subscriber}")
    public Materiel updateSubscriberMateriel(
            @PathVariable Long id_materiel,
            @PathVariable Long id_subscriber
    ){
        Materiel materiel= mater.findById(id_materiel).get();
        Subscriber subscriber=sub.findById(id_subscriber).get();
        materiel.updateSubscriberMateriel(subscriber);
        return mater.save(materiel);
    }

    //supprimer un materiel
    @DeleteMapping("/materiels/{id_materiel}")
    void deleteMateriels(@PathVariable Long id_materiel){mater.deleteById(id_materiel);}

}

