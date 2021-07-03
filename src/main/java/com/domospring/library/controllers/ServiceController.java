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

    @GetMapping(value="/AllServices")
    private List<Service> getAllSubscribers(){
        return service.findAll();
    }
    @PostMapping(value = "/Services")
    Service newService(@RequestBody Service newService){ return service.save(newService);}

    @GetMapping("/Services/{id_service}")
    Optional<Service> GetServices(@PathVariable Long id_service){
        return service.findById(id_service);
    }
    @PutMapping(value = "/Services/{id_service}")
    private Service UpdateService(@PathVariable(name = "id_service") Long id_service, @RequestBody Service ser){
        ser.setId_service(id_service);
        return  service.save(ser);
    }
    @DeleteMapping(value = "/Services/{id_service}")
    void deleteServices(@PathVariable Long id_service){service.deleteById(id_service);}
}

