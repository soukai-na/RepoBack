package com.domospring.library.controllers;

import com.domospring.library.dao.SubscriberRepository;
import com.domospring.library.model.Subscriber;
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

    @GetMapping(value="/AllSubscribers")
    private List<Subscriber> getAllSubscribers(){
        return sub.findAll();

    }
    @PostMapping("/Subscribers")
    Subscriber newSubscriber(@RequestBody Subscriber newSubscriber) {
        return sub.save(newSubscriber);
    }

    @GetMapping("/Subscribers/{id_subscriber}")
    Optional<Subscriber> GetSubscribers(@PathVariable Long id_subscriber) {

        return sub.findById(id_subscriber);
    }

    @PutMapping(value = "/Subscribers/{id_subscriber}")
    private Subscriber UpdateSubscriber(@PathVariable(name = "id_subscriber") Long id_subscriber, @RequestBody Subscriber b){
        b.setId_subscriber(id_subscriber);
        return sub.save(b);
    }

    @DeleteMapping("/Subscribers/{id_subscriber}")
    void deleteSubscribers(@PathVariable Long id_subscriber) {
        sub.deleteById(id_subscriber);
    }

}

