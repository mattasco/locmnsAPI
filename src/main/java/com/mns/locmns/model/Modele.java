package com.mns.locmns.model;

import com.fasterxml.jackson.annotation.*;
import com.mns.locmns.view.MaterielView;
import com.mns.locmns.view.ModeleView;
import com.mns.locmns.view.TypeMaterielView;
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
    @JsonView(ModeleView.class)
    private Integer id;
    @JsonView({ModeleView.class,TypeMaterielView.class, MaterielView.class})
    private String nom;
    @JsonView(ModeleView.class)
    private String documentation;
    @JsonView(ModeleView.class)
    private Integer caution;

    private String photo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="type_materiel_id")
    @JsonView({ModeleView.class,MaterielView.class})
    private TypeMateriel typeMateriel;

    @ManyToOne
    @JoinColumn(name = "marque_id")
    @JsonView({ModeleView.class,MaterielView.class})
    private Marque marque;
}