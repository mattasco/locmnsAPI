package com.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.mns.locmns.view.EmpruntView;
import com.mns.locmns.view.MaterielView;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({MaterielView.class, EmpruntView.class})
    private Integer id;

    @JsonView({MaterielView.class, EmpruntView.class})
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateDemande;

    @JsonView({MaterielView.class, EmpruntView.class})
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateDebutPret;

    @JsonView({MaterielView.class, EmpruntView.class})
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateFinPret;

    @JsonView({MaterielView.class, EmpruntView.class})
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateRetourEffectif;

    @JsonView({MaterielView.class, EmpruntView.class})
    private String dysfonctionnement;

    @JsonView({MaterielView.class, EmpruntView.class})
    private boolean valide;

    @JsonView({MaterielView.class, EmpruntView.class})
    private String utilisation;

    @ManyToOne
    @JoinColumn(name = "emprunteur_id")
    @JsonView({MaterielView.class, EmpruntView.class})
    private Utilisateur emprunteur;

    @ManyToOne
    @JoinColumn(name = "etat_materiel_id")
    @JsonView({MaterielView.class, EmpruntView.class})
    private EtatMateriel etatMateriel;

    @ManyToOne
    @JoinColumn(name = "admin_id" )
    @JsonView({MaterielView.class, EmpruntView.class})
    private Utilisateur admin;

    @ManyToOne
    @JoinColumn(name = "materiel_id")
    @JsonView(EmpruntView.class)
    private Materiel materiel;
}
