package com.mns.locmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mns.locmns.dao.EmpruntDao;
import com.mns.locmns.dao.MaterielDao;
import com.mns.locmns.dao.ModeleDao;
import com.mns.locmns.model.Emprunt;
import com.mns.locmns.model.Materiel;
import com.mns.locmns.model.Modele;
import com.mns.locmns.view.MaterielView;
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
    @Autowired
    private EmpruntDao empruntDao;
    @Autowired
    private MaterielDao materielDao;

    @Autowired
    private ModeleDao modeleDao;


    //trouve la liste de materiel correspondant à un type de materiel
    @GetMapping("/liste-materiel/{id}")
    public List<Materiel> listeMaterielByModele (@PathVariable Integer id) {
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
    @GetMapping("/admin/materielID/{id}")
    public Optional<Materiel> materiel(@PathVariable Integer id){return this.materielDao.findById(id);}

    //Create or Updatea
    @PostMapping("/admin/materiel")
    public ResponseEntity<Materiel> createMateriel(@RequestBody Materiel materiel){
        this.materielDao.saveAndFlush(materiel);
        return ResponseEntity.status(HttpStatus.CREATED).body(materiel);
    }

    @DeleteMapping("/admin/deletemateriel/{id}")
    public ResponseEntity<Integer> deleteMateriel(@PathVariable int id) {
        if(materielDao.existsById(id)){
            List<Emprunt> aSupprimer = empruntDao.findByMaterielId(id);
            for(int i = 0 ; i<aSupprimer.size();i++){
                empruntDao.deleteById(aSupprimer.get(i).getId());
            }
            this.materielDao.deleteById(id);
            return ResponseEntity.ok(id);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/admin/liste-materiel")
    @JsonView(MaterielView.class)
    public List<Materiel> allMateriel(){return materielDao.findAll();}

    @GetMapping("/admin/recherchemateriel/{value}")
    @JsonView(MaterielView.class)
    public List<Materiel> rechercheMateriel(@PathVariable String value){return materielDao.findRecherche(value);}


    @PostMapping("/admin/ajoutermateriel")
    @JsonView(MaterielView.class)
    public ResponseEntity<Materiel> ajoutermateriel(@RequestBody Materiel materiel){
        Materiel nouveauMateriel = new Materiel();
        nouveauMateriel.setNumSerie(materiel.getNumSerie());
        nouveauMateriel.setModele(modeleDao.findByNom(materiel.getModele().getNom()));
        materielDao.save(nouveauMateriel);
        return ResponseEntity.ok(nouveauMateriel);
    }
}