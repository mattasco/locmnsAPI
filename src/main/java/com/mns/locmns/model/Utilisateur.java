package com.mns.locmns.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Utilisateur {

    @Id
    private Integer idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String adresse;
    private Integer telephone;
    private String pseudo;
    private boolean isAdmin;
    private boolean estActif;
}