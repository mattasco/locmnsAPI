package com.mns.locmns.dao;

import com.fasterxml.jackson.annotation.JsonView;
import com.mns.locmns.model.Marque;
import com.mns.locmns.model.Modele;
import com.mns.locmns.view.ModeleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ModeleDao extends JpaRepository<Modele,Integer> {
    List<Modele> findByTypeMaterielId(Integer id);

    @Query("from Modele m where m.nom like :value% or m.typeMateriel.nom like :value% or m.marque.nom like :value%")
    List<Modele> findByRecherche(String value);

    Modele findByNom(String nom);
}
