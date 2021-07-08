package com.domospring.library.controllers;

import com.domospring.library.dao.AbonnementRepository;
import com.domospring.library.dao.ServiceRepository;
import com.domospring.library.dao.SubscriberRepository;
import com.domospring.library.model.Abonnement;
import com.domospring.library.model.Service;
import com.domospring.library.model.Subscriber;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("")
public class SubscriberController {
    @Autowired
    private SubscriberRepository sub;
    @Autowired
    private ServiceRepository ser;
    @Autowired
    private AbonnementRepository abm;

//afficher tous les subscribers
    @GetMapping(value="/subscribers")
    private List<Subscriber> getAllSubscribers(){
        return sub.findAll();

    }

    //afficher un subscriber par son id
    @GetMapping("/subscribers/{id_subscriber}")
    Optional<Subscriber> GetSubscribers(@PathVariable Long id_subscriber) {
        return sub.findById(id_subscriber);
    }

    //créer un subscriber dans un service
    @PostMapping("/services/{id_service}/subscribers")
    public Subscriber newSubscriber(@PathVariable Long id_service, @RequestBody Subscriber subscriber) throws NotFoundException {
        return ser.findById(id_service).map(service -> {
            subscriber.setService(service);
            return sub.save(subscriber);
        }).orElseThrow(()->new NotFoundException("service not found"));
    }



    //modifier un subscriber
    @PutMapping(value = "/services/{id_service}/subscribers/{id_subscriber}")
   public Subscriber updateSubscriber(
           @PathVariable Long id_subscriber,
           @PathVariable Long id_service,
           @RequestBody Subscriber subscribedUpdated
    ) throws NotFoundException {
        if(!ser.existsById(id_service)){
            throw  new NotFoundException("service not found");
        }
        return sub.findById(id_subscriber).map(
                subscriber -> {
                    subscriber.setNom_subscriber(subscribedUpdated.getNom_subscriber());
                    subscriber.setPrenom_subscriber(subscribedUpdated.getPrenom_subscriber());
                    subscriber.setNum_sim(subscribedUpdated.getNum_sim());
                    subscriber.setFonction(subscribedUpdated.getFonction());
                    return sub.save(subscriber);
                }
        ).orElseThrow(()->new NotFoundException("Subscriber not found"));
    }

//changer le service d'un subscriber
    @PutMapping(value = "/subscribers/{id_subscriber}/services/{id_service}")
    public Subscriber updateServiceSubscriber(
            @PathVariable Long id_subscriber,
            @PathVariable Long id_service){
        Subscriber subscriber=sub.findById(id_subscriber).get();
        Service service=ser.findById(id_service).get();
        subscriber.updateServiceSubscriber(service);
        return sub.save(subscriber);
    }

    //donner une abonnement à un subscriber
    @PutMapping(value="/subscribers/{id_subscriber}/abonnements/{id_abonnement}")
    public Subscriber updateAbonnementSubscriber(
            @PathVariable Long id_subscriber,
            @PathVariable Long id_abonnement){
        Subscriber subscriber=sub.findById(id_subscriber).get();
        Abonnement abonnement=abm.findById(id_abonnement).get();
        subscriber.updateAbonnementSubscriber(abonnement);
        return sub.save(subscriber);
    }

    //supprimer un subscriber dans un service
    @DeleteMapping(value = "/services/{id_service}/subscribers/{id_subscriber}")
            public String deleteSubscriber(@PathVariable Long id_subscriber,
                                   @PathVariable Long id_service) throws NotFoundException {
        if(!ser.existsById(id_service)){
            throw new NotFoundException("service not found");
        }
        return sub.findById(id_subscriber).map(
                subscriber -> {
                    sub.delete(subscriber);
                    return "deleted successfully";
                }
        ).orElseThrow(()->new NotFoundException("subscriber not found"));
    }

}

