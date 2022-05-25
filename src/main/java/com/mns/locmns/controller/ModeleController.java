package com.mns.locmns.controller;

import com.mns.locmns.dao.ModeleDao;
import com.mns.locmns.model.Materiel;
import com.mns.locmns.model.Modele;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ModeleController {
    private ModeleDao modeleDao;

    @Autowired
    public ModeleController(ModeleDao modeleDao){this.modeleDao=modeleDao;}

    @GetMapping("/liste-modele/{id}")
    public List<Modele> listeModele (@PathVariable Integer id) {
        return this.modeleDao.findByTypeMaterielId(id);
    }

    @GetMapping("/modele/{id}")
    public Modele modele(@PathVariable Integer id) {
        return this.modeleDao.findById(id).orElse(null);
    }
}
