package com.mns.locmns.controller;

import com.mns.locmns.dao.ModeleDao;
import com.mns.locmns.dao.UtilisateurDao;
import com.mns.locmns.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UtilisateurController {
    private UtilisateurDao utilisateurDao;

    @Autowired
    public UtilisateurController(UtilisateurDao utilisateurDao){this.utilisateurDao=utilisateurDao;}
}
