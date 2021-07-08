package com.domospring.library.controllers;

import com.domospring.library.dao.ServiceRepository;
import com.domospring.library.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("")
public class ServiceController {
    @Autowired
    private ServiceRepository service;

    //afficher les services
    @GetMapping(value="/services")
    private List<Service> getAllServices(){

        return service.findAll();
    }

    //afficher un service par son id
    @GetMapping("/services/{id_service}")
    Optional<Service> GetService(@PathVariable Long id_service){

        return service.findById(id_service);
    }

//créer un service
    @PostMapping(value = "/services")
    Service newService(@RequestBody Service newService){
        return service.save(newService);
    }


    //modifier un service
    @PutMapping(value = "/services/{id_service}")
    private Service UpdateService(@PathVariable(name = "id_service") Long id_service, @RequestBody Service ser){
        ser.setId_service(id_service);
        return  service.save(ser);
    }

    //supprimer un service
    @DeleteMapping(value = "/services/{id_service}")
    void deleteServices(@PathVariable Long id_service){service.deleteById(id_service);}
}

