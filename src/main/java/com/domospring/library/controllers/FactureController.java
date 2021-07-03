package com.domospring.library.controllers;

import com.domospring.library.dao.FactureRepository;
import com.domospring.library.model.Facture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("")
public class FactureController {
    @Autowired
    private FactureRepository facture;

    @GetMapping(value="/AllFactures")
    private List<Facture> getAllFactures(){
        return facture.findAll();
    }

    @PostMapping("/Factures")
    Facture newFacture(@RequestBody Facture newFacture){ return facture.save(newFacture);}

    @GetMapping("/Factures/{id_facture}")
    Optional<Facture> GetFacture(@PathVariable Long id_facture){
        return facture.findById(id_facture);
    }

    @PutMapping(value = "/Factures/{id_facture}")
    private Facture UpdateFacture(@PathVariable(name = "id_facture") Long id_facture, @RequestBody Facture f){
        f.setId_facture(id_facture);
        return facture.save(f);
    }

    @DeleteMapping("/Factures/{id_facture}")
    void deleteFactures(@PathVariable Long id_facture) {
        facture.deleteById(id_facture);
    }


}

