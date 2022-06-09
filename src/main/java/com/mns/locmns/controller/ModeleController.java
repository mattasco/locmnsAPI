package com.mns.locmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mns.locmns.dao.MarqueDao;
import com.mns.locmns.dao.MaterielDao;
import com.mns.locmns.dao.ModeleDao;
import com.mns.locmns.dao.TypeMaterielDao;
import com.mns.locmns.model.Materiel;
import com.mns.locmns.model.Modele;
import com.mns.locmns.view.ModeleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@CrossOrigin
@RestController
public class ModeleController {

    @Autowired
    private MarqueDao marqueDao;
    @Autowired
    private ModeleDao modeleDao;

    @Autowired
    private MaterielDao materielDao;

    @Autowired
    private TypeMaterielDao typeMaterielDao;

    @GetMapping("/liste-modele/{id}")
    public List<Modele> listeModeleByTypeMateriel (@PathVariable Integer id) {
        return this.modeleDao.findByTypeMaterielId(id);
    }

    @GetMapping("/admin/modele/{id}")
    public Modele modele(@PathVariable Integer id) {
        return this.modeleDao.findById(id).get();
    }

    @GetMapping("/admin/liste-modele")
    @JsonView(ModeleView.class)
    public List<Modele> listeModele(){return modeleDao.findAll();}

    @DeleteMapping("/admin/deletemodele/{id}")
    public ResponseEntity<Integer> deleteModele(@PathVariable Integer id){
        List<Materiel> listeMateriel= materielDao.findByModeleId(id);
        boolean associe = false;
        for (int i=0; i<listeMateriel.size();i++){
            if(listeMateriel.get(i).getModele().getNom()==modeleDao.findById(id).get().getNom()){
                associe=true;
            }
        }
        if (modeleDao.existsById(id) && associe==false) {
            this.modeleDao.deleteById(id);
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/admin/recherchemodele/{nom}")
    @JsonView(ModeleView.class)
    public List<Modele> rechercheModele(@PathVariable String nom){
        return modeleDao.findByRecherche(nom);
    }

    @PostMapping("/admin/modifiermodele/{id}")
    public ResponseEntity<Modele> modifierModele(@PathVariable Integer id, @RequestBody Modele modele){
        Modele nouveauModele = new Modele();
        Modele modeleAmodifier = modeleDao.getById(id);
        nouveauModele.setId(modeleAmodifier.getId());
        nouveauModele.setCaution(modele.getCaution());
        nouveauModele.setDocumentation(modele.getDocumentation());
        nouveauModele.setNom(modele.getNom());
        nouveauModele.setMarque(marqueDao.findByNom(modele.getMarque().getNom()));
        nouveauModele.setTypeMateriel(typeMaterielDao.findByNom(modele.getTypeMateriel().getNom()));
        modeleDao.save(nouveauModele);
        return ResponseEntity.ok(nouveauModele);
    }

    @PostMapping("/admin/ajoutermodele")
    public ResponseEntity<Modele> ajoutermodele(@RequestBody Modele modele){
        Modele nouveauModele = new Modele();
        nouveauModele.setCaution(modele.getCaution());
        nouveauModele.setDocumentation(modele.getDocumentation());
        nouveauModele.setNom(modele.getNom());
        nouveauModele.setMarque(marqueDao.findByNom(modele.getMarque().getNom()));
        nouveauModele.setTypeMateriel(typeMaterielDao.findByNom(modele.getTypeMateriel().getNom()));
        modeleDao.save(nouveauModele);
        return ResponseEntity.ok(nouveauModele);
    }
}
