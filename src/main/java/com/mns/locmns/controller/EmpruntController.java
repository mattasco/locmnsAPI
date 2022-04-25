package com.mns.locmns.controller;

import com.mns.locmns.dao.EmpruntDao;
import com.mns.locmns.model.Emprunt;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class EmpruntController {
    private EmpruntDao empruntDao;

    @Autowired
    public EmpruntController(EmpruntDao empruntDao){this.empruntDao=empruntDao;}


    @PostMapping("/emprunt")
    public String createEmprunt(@RequestBody Emprunt emprunt){
        this.empruntDao.save(emprunt);
        return "emprunt ajouté";
    }

    @GetMapping("/emprunt/{materielId}")
    public Emprunt empruntByMateriel(@PathVariable Integer materielId){return this.empruntDao.findByMaterielId(materielId).orElse(null);}
}
