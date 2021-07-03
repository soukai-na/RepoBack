package com.domospring.library.controllers;

import com.domospring.library.dao.MatereilRepository;
import com.domospring.library.model.Materiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("")
public class MaterielController {
    @Autowired
    private MatereilRepository materiel;

    @GetMapping(value="/AllMateriels")
    private List<Materiel> getAllMateriels(){
        return materiel.findAll();
    }
    @PostMapping("/Materiels")
    Materiel newMateriel(@RequestBody Materiel newMateriel){return materiel.save(newMateriel);}

    @GetMapping("/Materiels/{id_materiel}")
    Optional<Materiel> GetMateriels(@PathVariable Long id_materiel){
        return materiel.findById(id_materiel);
    }

    @PutMapping(value = "/Materiels/{id_materiel}")
    private  Materiel UpdateMateriel(@PathVariable(name = "id_materiel") Long id_materiel, @RequestBody Materiel mtl){
        mtl.setId_materiel(id_materiel);
        return materiel.save(mtl);
    }

    @DeleteMapping("/Materiels/{id_materiel}")
    void deleteMateriels(@PathVariable Long id_materiel){materiel.deleteById(id_materiel);}

}

