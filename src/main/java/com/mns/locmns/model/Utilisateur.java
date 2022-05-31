package com.mns.locmns.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Utilisateur {
    @Id
    private Integer id;
    private String login;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String adresse;
    private Integer telephone;
    private boolean admin;
    private Integer numToken;
}