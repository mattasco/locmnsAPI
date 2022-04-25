package com.mns.locmns.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EtatMateriel {
    @Id
    private Integer idEtatExemplaire;
    private String etat;
}
