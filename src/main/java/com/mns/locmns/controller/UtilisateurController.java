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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class UtilisateurController {
    private UtilisateurDao utilisateurDao;

    @Autowired   // injection de dépendances ( classe qui dépendent d'autre classe )
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    public UtilisateurController(UtilisateurDao utilisateurDao){this.utilisateurDao=utilisateurDao;}

    @PostMapping("/connexion")            // renvoyer un token si l'utilisateur existe dans la BDD
    public Map<String, String> connexion(@RequestBody Utilisateur utilisateur) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getLogin(),
                            utilisateur.getPassword()
                    )
            );
        }catch (BadCredentialsException e) {
            Map<String,String> retour=new HashMap<>();
            retour.put("erreur","mauvais log mdp");
            return retour;
        }

        UserDetail userDetails = userDetailService.loadUserByUsername(utilisateur.getLogin());

        Map<String,String> retour= new HashMap<>();
        retour.put("token",jwtUtils.generateToken(userDetails));

        return retour;
    }

    @GetMapping("/deconnexion")
    public ResponseEntity<String> deconnexion(@RequestHeader("Authorization")String jwt){
        String token =jwt.substring(7);
        int idUtilisateurConnecte= (int)jwtUtils.getTokenBody(token).get("id");

        Optional<Utilisateur> utilisateurOpptional= utilisateurDao.findById(idUtilisateurConnecte);
        if(utilisateurOpptional.isPresent()){
            utilisateurOpptional.get().setNumToken(utilisateurOpptional.get().getNumToken()+1);
            utilisateurDao.save(utilisateurOpptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}
