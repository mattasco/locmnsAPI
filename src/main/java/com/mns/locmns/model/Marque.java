package com.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mns.locmns.view.VueMateriel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Marque {

    @Id
    @JsonView(VueMateriel.class)
    private Integer id;

    @JsonView(VueMateriel.class)
    private String nom;
}
