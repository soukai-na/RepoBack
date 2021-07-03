package com.domospring.library.controllers;

import com.domospring.library.dao.ProfilRepository;
import com.domospring.library.model.Profil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("")
public class ProfilController {
    @Autowired
    private ProfilRepository profil;

    @GetMapping(value="/AllProfils")
    private List<Profil> getAllProfils(){
        return profil.findAll();
    }

    @PostMapping("/Profils")
    Profil newProfil(@RequestBody Profil newProfil){ return profil.save(newProfil);}

    @GetMapping("/Profiles/{id_profil}")
    Optional<Profil> GetProfil(@PathVariable Long id_profil){
        return profil.findById(id_profil);
    }

    @PutMapping(value = "/Profile/{id_profil}")
    private Profil UpdateProfil(@PathVariable(name = "id_profil") Long id_profil, @RequestBody Profil pr){
        pr.setId_profil(id_profil);
        return profil.save(pr);
    }

    @DeleteMapping("/Profiles/{id_profil}")
    void deleteProfils(@PathVariable Long id_profil) {
        profil.deleteById(id_profil);
    }
}

