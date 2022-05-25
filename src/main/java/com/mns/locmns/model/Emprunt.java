package com.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmprunt;
    @JsonFormat(pattern="dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateDemande;
    @JsonFormat(pattern="dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateDebutPret;
    @JsonFormat(pattern="dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateFinPret;
    @Nullable
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateRetourEffectif;
    @Nullable
    private String dysfonctionnement;
    private boolean valide;
    private String utilisation;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "emprunteur_id")
    private Utilisateur emprunteur;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "etat_materiel_id")
    private EtatMateriel etatMateriel;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "admin_id" )
    private Utilisateur admin;

    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;


}
