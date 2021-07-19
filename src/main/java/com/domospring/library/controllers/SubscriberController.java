package com.domospring.library.controllers;

import com.domospring.library.dao.AbonnementRepository;
import com.domospring.library.dao.FactureRepository;
import com.domospring.library.dao.ServiceRepository;
import com.domospring.library.dao.SubscriberRepository;
import com.domospring.library.model.Abonnement;
import com.domospring.library.model.Facture;
import com.domospring.library.model.Service;
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
public class SubscriberController {
    @Autowired
    private SubscriberRepository sub;
    @Autowired
    private ServiceRepository ser;
    @Autowired
    private AbonnementRepository abm;
    @Autowired
    private FactureRepository fct;

    //afficher tous les subscribers
    @GetMapping(value="/subscribers")
    private ResponseEntity<List<Subscriber>> getAllSubscribers(){
        try{
            List<Subscriber> listeSubscriber=sub.findAll();
            log.info("subscribers importés avec success");
            return new ResponseEntity(listeSubscriber, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur importation des subscribers");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //afficher un subscriber par son id
    @GetMapping("/subscribers/{id_subscriber}")
    private ResponseEntity<Optional<Subscriber>> GetSubscribers(@PathVariable Long id_subscriber) {
        try{
            Optional<Subscriber>  subsc=sub.findById(id_subscriber);
            log.info("subscriber importé avec success");
            return new ResponseEntity(subsc, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur importation de subscriber");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //créer un subscriber dans un service
    @PostMapping("/services/{id_service}/subscribers")
    public ResponseEntity<Subscriber> newSubscriber(@PathVariable Long id_service, @RequestBody Subscriber subscriber){
        try{
            ser.findById(id_service).map(service -> {
                subscriber.setService(service);
                return sub.save(subscriber);
            });
            log.info("subscriber créée avec success");
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur création de subscriber");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //modifier un subscriber
    @PutMapping(value = "/services/{id_service}/subscribers/{id_subscriber}")
    public ResponseEntity<Subscriber> updateSubscriber(
            @PathVariable Long id_subscriber,
            @PathVariable Long id_service,
            @RequestBody Subscriber subscribedUpdated
    ){
        try{
            if(!ser.existsById(id_service)){
                log.error("service not found");
            }
            sub.findById(id_subscriber).map(
                    subscriber -> {
                        subscriber.setNom_subscriber(subscribedUpdated.getNom_subscriber());
                        subscriber.setPrenom_subscriber(subscribedUpdated.getPrenom_subscriber());
                        subscriber.setNum_sim(subscribedUpdated.getNum_sim());
                        subscriber.setFonction(subscribedUpdated.getFonction());
                        return sub.save(subscriber);
                    }
            );

            log.info("subscriber modifié avec success");
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur modification de subscriber");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //affecter une abonnement à un subscriber
    @PostMapping(value="/subscribers/{id_subscriber}/abonnements/{id_abonnement}")
    public ResponseEntity<Subscriber> updateAbonnementSubscriber(
            @PathVariable Long id_subscriber,
            @PathVariable Long id_abonnement){
        try {
            Subscriber subscriber = sub.findById(id_subscriber).get();
            Abonnement abonnement = abm.findById(id_abonnement).get();
            subscriber.updateAbonnementSubscriber(abonnement);
            Subscriber abonnSub=sub.save(subscriber);
            log.info("abonnement affecté au subscriber  avec success");
            return new ResponseEntity(abonnSub, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur d'affectaion d'abonnement au subscriber");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //supprimer un subscriber dans un service
    @DeleteMapping(value = "/services/{id_service}/subscribers/{id_subscriber}")
    public ResponseEntity<String>deleteSubscriber(@PathVariable Long id_subscriber,
                                                  @PathVariable Long id_service){
        try{
            if(!ser.existsById(id_service)){
                log.error("service not found");
            }
            sub.findById(id_subscriber).map(
                    subscriber -> {
                        sub.delete(subscriber);
                        log.info(" subscriber supprimé");
                        return new ResponseEntity( HttpStatus.OK);
                    }
            );
            log.info(" subscriber supprimé");
            return new ResponseEntity( HttpStatus.OK);

        }catch(Exception e){
            log.error("erreur d'affectaion d'abonnement au subscriber");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

