package com.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mns.locmns.view.VueMateriel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Modele {

    @Id
    @JsonView(VueMateriel.class)
    private Integer id;

    @JsonView(VueMateriel.class)
    private String nom;

    @ManyToOne
    @JsonView(VueMateriel.class)
    @JoinColumn(name ="type_materiel_id")
    private TypeMateriel typeMateriel;

    @ManyToOne
    @JsonView(VueMateriel.class)
    @JoinColumn(name = "marque_id")
    private Marque marque;
}
