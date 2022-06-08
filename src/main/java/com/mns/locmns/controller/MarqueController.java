package com.mns.locmns.controller;

import com.mns.locmns.dao.MarqueDao;
import com.mns.locmns.dao.ModeleDao;
import com.mns.locmns.model.Marque;
import com.mns.locmns.model.Modele;
import com.mns.locmns.model.TypeMateriel;
import com.mns.locmns.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MarqueController {
    @Autowired
    private MarqueDao marqueDao;

    @Autowired
    private ModeleDao modeleDao;

    @GetMapping("/admin/liste-marques")
    public List<Marque> listeMarques(){return  marqueDao.findAll();}

    @DeleteMapping("/admin/marque/{id}")
    public ResponseEntity<Integer> deleteMarque(@PathVariable Integer id){
        List<Modele> listeModeles= modeleDao.findAll();
        boolean associe = false;
        for (int i=0; i<listeModeles.size();i++){
            if(listeModeles.get(i).getMarque().getNom()==marqueDao.findById(id).get().getNom()){
                associe=true;
            }
        }
        if (marqueDao.existsById(id) && associe==false) {
            this.marqueDao.deleteById(id);
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/admin/recherchemarque/{value}")
    public List<Marque> rechercheMarque(@PathVariable String value){
        return marqueDao.findByNomLike(value);
    }

    @PostMapping("/admin/marque")
    public ResponseEntity<Marque> ajouterMarque(@RequestBody Marque marque){
        marqueDao.save(marque);
        return ResponseEntity.ok(marque);
    }

    @GetMapping("/admin/marque/{id}")
    public Marque getMarqueById(@PathVariable Integer id){
        return marqueDao.getById(id);
    }

    @PostMapping("admin/modifiermarque/{id}")
    public ResponseEntity<Marque> modfifierMarque(@PathVariable Integer id, @RequestBody Marque marque){
        if (marqueDao.findById(id).isPresent()) {
            Marque aModifier = marqueDao.findById(id).get();
            aModifier.setNom(marque.getNom());
            marqueDao.save(aModifier);
            return ResponseEntity.ok(aModifier);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
