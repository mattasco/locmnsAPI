package com.mns.locmns.controller;

import com.mns.locmns.dao.EmpruntDao;
import com.mns.locmns.dao.MaterielDao;
import com.mns.locmns.dao.UtilisateurDao;
import com.mns.locmns.model.Emprunt;
import com.mns.locmns.model.Materiel;
import com.mns.locmns.model.Utilisateur;
import com.mns.locmns.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@CrossOrigin
@RestController
public class EmpruntController {
    @Autowired
    private EmpruntDao empruntDao;

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Autowired
    private  MaterielDao materielDao;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/emprunt/{id}")
    public ResponseEntity<Emprunt> createEmprunt(@RequestBody Emprunt emprunt, @RequestHeader("Authorization") String jwt,@PathVariable Integer id){
        String token = jwt.substring(7);
        int idEmprunteur=(int) jwtUtils.getTokenBody(token).get("id");
        Optional<Utilisateur> emprunteur=utilisateurDao.findById(idEmprunteur);
        Emprunt nouvelEmprunt=new Emprunt();
        Date dateDuJour = Calendar.getInstance().getTime();
        Optional<Materiel> materiel = materielDao.findOneByModeleId(id);
        if(materiel.isPresent()) {
            nouvelEmprunt.setMateriel(materiel.get());
            nouvelEmprunt.setEmprunteur(emprunteur.get());
            nouvelEmprunt.setDateDebutPret(emprunt.getDateDebutPret());
            nouvelEmprunt.setDateFinPret(emprunt.getDateFinPret());
            nouvelEmprunt.setUtilisation(emprunt.getUtilisation());
            nouvelEmprunt.setDateDemande(dateDuJour);
            empruntDao.save(nouvelEmprunt);
            return ResponseEntity.ok(nouvelEmprunt);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/empruntByIdEmprunt/{id}")
    public Optional<Emprunt> empruntByIdEmprunt(@PathVariable Integer id){
        return this.empruntDao.findById(id);
    }

    @GetMapping("/emprunt/{materielId}")
    public Emprunt empruntByMateriel(@PathVariable Integer materielId){return this.empruntDao.findByMaterielId(materielId).orElse(null);}

    @GetMapping("/empruntnonvalide")
    public Iterable<Emprunt> empruntNonValide(){return  this.empruntDao.findByValideFalse();}

    @PostMapping("/valideemprunt")
    public ResponseEntity<Emprunt> valideEmprunt(@RequestBody Integer empruntId, @RequestHeader("Authorization") String jwt){
        Emprunt empruntAValider = empruntDao.getById(empruntId);
        String token = jwt.substring(7);
        int idAdmin=(int) jwtUtils.getTokenBody(token).get("id");
        Optional<Utilisateur> admin=utilisateurDao.findById(idAdmin);
        if(admin.isPresent()) {
            empruntAValider.setValide(true);
            empruntAValider.setAdmin(admin.get());
            empruntDao.save(empruntAValider);
            return ResponseEntity.ok(empruntAValider);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/supprimeEmprunt/{empruntId}")
    public ResponseEntity<Emprunt> supprimeEmprunt(@PathVariable Integer empruntId){
        Optional<Emprunt> empruntASupprimer = empruntDao.findById(empruntId);
        if(empruntASupprimer.isPresent()){
            empruntDao.deleteById(empruntId);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/empruntencours")
    public Iterable<Emprunt> empruntEnCours(){return empruntDao.findEnCours();}

    @PostMapping("/retouremprunt")
    public ResponseEntity<Emprunt> retourEmprunt(@RequestBody Integer id){
        Emprunt empruntRetour = empruntDao.getById(id);
        Date date=Calendar.getInstance().getTime();
        empruntRetour.setDateRetourEffectif(date);
        empruntDao.save(empruntRetour);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/emprunt")
    public Iterable<Emprunt> mesEmprunts(@RequestHeader("Authorization") String jwt){
        String token = jwt.substring(7);
        int idUtilisateur=(int) jwtUtils.getTokenBody(token).get("id");
        return empruntDao.findEncoursById(idUtilisateur);
    }

    @PostMapping("/dysfonctionnement/{id}")
    public ResponseEntity<Emprunt> dysfonctionnement(@RequestBody Emprunt emprunt,@PathVariable Integer id){
        Optional<Emprunt> empruntTrouve = empruntDao.findById(id);
        Emprunt empruntAjour = empruntTrouve.get();
        empruntAjour.setDysfonctionnement(emprunt.getDysfonctionnement());
        empruntDao.save(empruntAjour);
        return ResponseEntity.ok(empruntAjour);
    }
}
