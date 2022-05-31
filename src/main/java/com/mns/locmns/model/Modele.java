package com.mns.locmns.model;

import lombok.Data;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Modele {

    @Id
    private Integer id;

    private String nom;

    private String documentation;

    private Integer caution;

    private String photo;

    @ManyToOne
    @JoinColumn(name ="type_materiel_id")
    private TypeMateriel typeMateriel;

    @ManyToOne
    @JoinColumn(name = "marque_id")
    private Marque marque;
}