package com.domospring.library.controllers;

import com.domospring.library.dao.ServiceRepository;
import com.domospring.library.model.Service;
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
public class ServiceController {
    @Autowired
    private ServiceRepository service;

    //afficher tous les services
    @GetMapping(value="/services")
    private ResponseEntity<List<Service>> getAllServices(){
        try{
            List<Service> listeService=service.findAll();
            log.info("services importés avec success");
            return new ResponseEntity(listeService, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur importation des services");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //afficher un service par son id
    @GetMapping("/services/{id_service}")
    private ResponseEntity<Optional<Service>> GetService(@PathVariable Long id_service){
        try{
            Optional<Service> unService=service.findById(id_service);
            log.info("service importé avec success");
            return new ResponseEntity(unService, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur importation du service");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

//créer un service
    @PostMapping(value = "/services")
    private ResponseEntity<Service> newService(@RequestBody Service newService){
        try{
            Service createService= service.save(newService);
            log.info("service créée avec success");
            return new ResponseEntity(createService, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur création du service");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //modifier un service
    @PutMapping(value = "/services/{id_service}")
    private ResponseEntity<Service> UpdateService(@PathVariable(name = "id_service") Long id_service, @RequestBody Service ser){
        try{
            ser.setId_service(id_service);
            Service putService=  service.save(ser);
            log.info("service modifié avec success");
            return new ResponseEntity(putService, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur modification du service");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //supprimer un service
    @DeleteMapping(value = "/services/{id_service}")
    public  ResponseEntity<?> deleteServices(@PathVariable Long id_service){
        try{
            service.deleteById(id_service);
            log.info("service supprimé");
            return new ResponseEntity<String>( HttpStatus.OK);
        }catch(Exception e){
            log.error("erreur de suppresion d'un service contient un subscriber");
            return   new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

