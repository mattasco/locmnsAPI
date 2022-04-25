package com.mns.locmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mns.locmns.dao.MaterielDao;
import com.mns.locmns.model.Materiel;
import com.mns.locmns.view.VueMateriel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MaterielController {
    private MaterielDao materielDao;

    @Autowired
    public MaterielController(MaterielDao materielDao){this.materielDao=materielDao;}

    @GetMapping("/liste-materiel")
    @JsonView(VueMateriel.class)
    public List<Materiel> listeMateriel () {
        return this.materielDao.findAll();
    }

    @GetMapping("/materiel/{id}")
    @JsonView(VueMateriel.class)
    public Materiel materiel(@PathVariable Integer id) {

        return this.materielDao.findById(id).orElse(null);
    }

    @PostMapping("/materiel")
    public String createMateriel(@RequestBody Materiel materiel){
        this.materielDao.save(materiel);
        return "ok";
    }
}