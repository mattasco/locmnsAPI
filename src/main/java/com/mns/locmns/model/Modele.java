package com.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Modele {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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