package com.mns.locmns.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Materiel {

    @Id
    private Integer id;
    private String numSerie;

    @ManyToOne
    @JoinColumn(name="modele_id")
    private Modele modele;

    @ManyToOne
    @JoinColumn(name="etat_materiel_id")
    private EtatMateriel etatMateriel;

    private boolean disponible;
}
