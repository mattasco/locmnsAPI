package com.mns.locmns.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EtatMateriel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String etat;
    private String observations;
}
