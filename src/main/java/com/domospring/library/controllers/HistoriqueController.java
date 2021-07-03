package com.domospring.library.controllers;

import com.domospring.library.dao.HistoriqueRepository;
import com.domospring.library.model.Historique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("")
public class HistoriqueController {
    @Autowired
    private HistoriqueRepository historique;

    @GetMapping(value="/AllHistoriques")
    private List<Historique> getAllHistoriques(){return historique.findAll();}

    @PostMapping("/Historiques")
    Historique newHistorique(@RequestBody Historique newHistorique){return historique.save(newHistorique);}

    @GetMapping("/Historiques/{id_action}")
    Optional<Historique> GetHistoriques(@PathVariable long id_action){ return historique.findById(id_action);}

    @DeleteMapping("/Historiques/{id_historique}")
    void  deleteHistoriques(@PathVariable Long id_historique){historique.deleteById(id_historique);}
}
