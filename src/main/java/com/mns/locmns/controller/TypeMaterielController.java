package com.mns.locmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mns.locmns.dao.ModeleDao;
import com.mns.locmns.dao.TypeMaterielDao;
import com.mns.locmns.model.Modele;
import com.mns.locmns.model.TypeMateriel;
import com.mns.locmns.model.Utilisateur;
import com.mns.locmns.view.TypeMaterielView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.TypeElement;
import java.util.List;

@CrossOrigin
@RestController
public class  TypeMaterielController {
    @Autowired
    private TypeMaterielDao typeMaterielDao;
    @Autowired
    private ModeleDao modeleDao;

    @GetMapping("/admin/liste-type-materiel")
    @JsonView(TypeMaterielView.class)
    public List<TypeMateriel> listeMateriel () {
        return this.typeMaterielDao.findAll();
    }

    @DeleteMapping("admin/type-materiel/{id}")
    public ResponseEntity<Integer> deleteUtilisateur(@PathVariable Integer id) {
        List<Modele> listeModeles= modeleDao.findAll();
        boolean associe = false;
        for (int i=0; i<listeModeles.size();i++){
            if(listeModeles.get(i).getTypeMateriel().getNom()==typeMaterielDao.findById(id).get().getNom()){
                associe=true;
            }
        }
        if (typeMaterielDao.existsById(id) && associe==false) {
            this.typeMaterielDao.deleteById(id);
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/admin/typemateriel")
    public ResponseEntity<TypeMateriel> createUtilisateur(@RequestBody TypeMateriel typeMateriel) {
        typeMaterielDao.save(typeMateriel);
        return ResponseEntity.ok(typeMateriel);
    }

    @GetMapping("/admin/type-materiel/{id}")
    public TypeMateriel getType(@PathVariable Integer id){
        return typeMaterielDao.findById(id).get();
    }

    @PostMapping("admin/modifiertype/{id}")
    public ResponseEntity<TypeMateriel> modfifierType(@PathVariable Integer id, @RequestBody TypeMateriel typeMateriel){
        if (typeMaterielDao.findById(id).isPresent()) {
            TypeMateriel aModifier = typeMaterielDao.findById(id).get();
            aModifier.setNom(typeMateriel.getNom());
            typeMaterielDao.save(aModifier);
            return ResponseEntity.ok(aModifier);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}