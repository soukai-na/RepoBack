package com.domospring.library.controllers;

import com.domospring.library.dao.HistoriqueRepository;
import com.domospring.library.model.Historique;
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
public class HistoriqueController {
    @Autowired
    private HistoriqueRepository historique;

    //Afficher l'historique
    @GetMapping(value="/historiques")
    private ResponseEntity<List<Historique>> getAllHistoriques(){
        try{
            List<Historique> listeHistorique=historique.findAll();
            log.info("historique importé avec success");
            return new ResponseEntity(listeHistorique, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur importation d'historiques");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/historiques")
    private ResponseEntity<Historique> newHistorique(@RequestBody Historique newHistorique){
        try{
            Historique history= historique.save(newHistorique);
            log.info("historique ajouté");
            return new ResponseEntity(history, HttpStatus.OK);
        } catch(Exception e){
            log.error("erreur sous l'ajout d'historique");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
