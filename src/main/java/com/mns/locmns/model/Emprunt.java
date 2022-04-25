package com.mns.locmns.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Emprunt {

    @Id
    private Integer idEmprunt;
    private Date dateDemande;
    private Date dateDebutPret;
    private Date dateFinPret;
    private Date dateRetourEffectif;
    private String empruntObservations;

    @ManyToOne
    @JoinColumn(name= "emprunteur_id")
    private Utilisateur emprunteur;

    @ManyToOne
    @JoinColumn(name="materiel_id")
    private Materiel materiel;

    @ManyToOne
    @JoinColumn(name="etat_materiel_id")
    private EtatMateriel etatMateriel;

    @ManyToOne
    @JoinColumn(name="admin_id")
    private Utilisateur admin;
}
