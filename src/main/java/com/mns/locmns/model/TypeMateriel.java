package com.mns.locmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mns.locmns.view.MaterielView;
import com.mns.locmns.view.ModeleView;
import com.mns.locmns.view.TypeMaterielView;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TypeMateriel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ModeleView.class,TypeMaterielView.class})
    private Integer id;
    @JsonView({ModeleView.class,TypeMaterielView.class, MaterielView.class})
    private String nom;

    private String photo;

    @OneToMany(mappedBy = "typeMateriel",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonView(TypeMaterielView.class)
    private List<Modele> listeModeles=new ArrayList<>();
}
