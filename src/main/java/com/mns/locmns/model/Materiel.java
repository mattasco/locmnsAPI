package com.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mns.locmns.view.EmpruntView;
import com.mns.locmns.view.MaterielView;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({MaterielView.class, EmpruntView.class})
    private Integer id;

    @JsonView({MaterielView.class, EmpruntView.class})
    private String numSerie;

    @ManyToOne
    @JoinColumn(name="modele_id")
    @JsonView({MaterielView.class, EmpruntView.class})
    private Modele modele;

    @ManyToOne
    @JoinColumn(name="etat_materiel_id")
    @JsonView({MaterielView.class, EmpruntView.class})
    private EtatMateriel etatMateriel;

    @OneToMany(mappedBy = "materiel",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonView(MaterielView.class)
    private List<Emprunt> emprunts;
}
