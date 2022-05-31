package com.mns.locmns.controller;

import com.mns.locmns.dao.MaterielDao;
import com.mns.locmns.model.Materiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class MaterielController {

    private MaterielDao materielDao;

    @Autowired
    public MaterielController(MaterielDao materielDao){this.materielDao=materielDao;}

    //trouve la liste de materiel correspondant à un type de materiel
    @GetMapping("/liste-materiel/{id}")
    public List<Materiel> listeMateriel (@PathVariable Integer id) {
        return this.materielDao.findByModeleId(id);
    }

    //trouve le premier materiel disponible selon l'id modele
    @GetMapping("/materiel/{id}")
    public ResponseEntity<Materiel> materielDisponible(@PathVariable Integer id){
        Optional<Materiel> retour = this.materielDao.findOneByModeleId(id);

        if(retour.isPresent()){
            return ResponseEntity.ok(retour.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    //trouve le materiel selon son id
    @GetMapping("/materielID/{id}")
    public Optional<Materiel> materiel(@PathVariable Integer id){return this.materielDao.findById(id);}

    //Create or Updatea
    @PostMapping("/admin/materiel")
    public ResponseEntity<Materiel> createMateriel(@RequestBody Materiel materiel){
        this.materielDao.saveAndFlush(materiel);
        return ResponseEntity.status(HttpStatus.CREATED).body(materiel);
    }

    @DeleteMapping("/admin/materiel/{id}")
    public ResponseEntity<Integer> deleteMateriel(@PathVariable int id) {

        if(materielDao.existsById(id)){
            this.materielDao.deleteById(id);
            return ResponseEntity.ok(id);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}