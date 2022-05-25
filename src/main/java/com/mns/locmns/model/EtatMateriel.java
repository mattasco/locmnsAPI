package com.mns.locmns.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
@Data
public class EtatMateriel {
    @Id
    private Integer id;
    private String etat;
    private String observations;
}
