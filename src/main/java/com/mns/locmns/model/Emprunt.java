package com.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({MaterielView.class, EmpruntView.class})
    private Integer id;

    @JsonView({MaterielView.class, EmpruntView.class})
    private Date dateDemande;

    @JsonView({MaterielView.class, EmpruntView.class})
    private Date dateDebutPret;

    @JsonView({MaterielView.class, EmpruntView.class})
    private Date dateFinPret;

    @JsonView({MaterielView.class, EmpruntView.class})
    @Nullable
    private Date dateRetourEffectif;

    @JsonView({MaterielView.class, EmpruntView.class})
    @Nullable
    private String dysfonctionnement;

    @JsonView({MaterielView.class, EmpruntView.class})
    private boolean valide;

    @JsonView({MaterielView.class, EmpruntView.class})
    private String utilisation;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "emprunteur_id")
    @JsonView({MaterielView.class, EmpruntView.class})
    private Utilisateur emprunteur;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "etat_materiel_id")
    @JsonView({MaterielView.class, EmpruntView.class})
    private EtatMateriel etatMateriel;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "admin_id" )
    @JsonView({MaterielView.class, EmpruntView.class})
    private Utilisateur admin;

    @ManyToOne
    @JoinColumn(name = "materiel_id")
    @JsonView(EmpruntView.class)
    private Materiel materiel;
}
