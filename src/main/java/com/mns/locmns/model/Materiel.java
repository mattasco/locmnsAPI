package com.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mns.locmns.view.VueMateriel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Materiel {

    @Id
    @JsonView(VueMateriel.class)
    private Integer id;

    @JsonView(VueMateriel.class)
    private String photo;

    @JsonView(VueMateriel.class)
    private String doc;

    @JsonView(VueMateriel.class)
    private Integer prixAchat;

    @ManyToOne
    @JsonView(VueMateriel.class)
    @JoinColumn(name="modele_id")
    private Modele modele;

}
