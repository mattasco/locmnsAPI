package com.mns.locmns.controller;
import com.mns.locmns.dao.UtilisateurDao;
import com.mns.locmns.model.Utilisateur;
import com.mns.locmns.security.JwtUtils;
import com.mns.locmns.security.UserDetail;
import com.mns.locmns.security.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class UtilisateurController {
    @Autowired
    private UtilisateurDao utilisateurDao;

    @Autowired   // injection de dépendances ( classe qui dépendent d'autre classe )
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/connexion")            // renvoyer un token si l'utilisateur existe dans la BDD
    public Map<String, String> connexion(@RequestBody Utilisateur utilisateur) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getLogin(),
                            utilisateur.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            Map<String, String> retour = new HashMap<>();
            retour.put("erreur", "mauvais log mdp");
            return retour;
        }

        UserDetail userDetails = userDetailService.loadUserByUsername(utilisateur.getLogin());

        Map<String, String> retour = new HashMap<>();
        retour.put("token", jwtUtils.generateToken(userDetails));
        return retour;
    }

    @GetMapping("/deconnexion")
    public ResponseEntity<String> deconnexion(@RequestHeader("Authorization") String jwt) {
        String token = jwt.substring(7);
        int idUtilisateurConnecte = (int) jwtUtils.getTokenBody(token).get("id");

        Optional<Utilisateur> utilisateurOpptional = utilisateurDao.findById(idUtilisateurConnecte);
        if (utilisateurOpptional.isPresent()) {
            utilisateurOpptional.get().setNumToken(utilisateurOpptional.get().getNumToken() + 1);
            utilisateurDao.save(utilisateurOpptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/liste-utilisateur")
    public List<Utilisateur> listeUtilisateurs() {
        return utilisateurDao.findAll();
    }

    @DeleteMapping("admin/utilisateur/{id}")
    public ResponseEntity<Integer> deleteUtilisateur(@PathVariable Integer id) {
        if (utilisateurDao.existsById(id)) {
            this.utilisateurDao.deleteById(id);
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/admin/utilisateur")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateurDao.save(utilisateur);
        return ResponseEntity.ok(utilisateur);
    }

    @GetMapping("/admin/utilisateur/{id}")
    public Utilisateur getUtilisateurById(@PathVariable Integer id) {
        return utilisateurDao.findById(id).get();
    }

    @PostMapping("/admin/modifierutilisateur/{id}")
    public ResponseEntity<Utilisateur> modifierUtilisateur(@PathVariable Integer id, @RequestBody Utilisateur utilisateur) {
        if (utilisateurDao.findById(id).isPresent()) {
            Utilisateur aModifier = utilisateurDao.findById(id).get();
            aModifier.setLogin(utilisateur.getLogin());
            aModifier.setNom(utilisateur.getNom());
            aModifier.setPrenom(utilisateur.getPrenom());
            aModifier.setEmail(utilisateur.getEmail());
            aModifier.setAdresse(utilisateur.getAdresse());
            aModifier.setTelephone(utilisateur.getTelephone());
            aModifier.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            utilisateurDao.save(aModifier);
            return ResponseEntity.ok(aModifier);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/admin/recherche/{value}")
    public List<Utilisateur> recherhceUtilisateur(@PathVariable String value){
        return utilisateurDao.findByNomOrPrenom(value);
    }

}
