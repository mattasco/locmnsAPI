package com.mns.locmns.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Marque {
    @Id
    private Integer id;

    private String nom;

}
