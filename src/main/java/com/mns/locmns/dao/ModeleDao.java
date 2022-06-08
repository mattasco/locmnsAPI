package com.mns.locmns.dao;

import com.mns.locmns.model.Marque;
import com.mns.locmns.model.Modele;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ModeleDao extends JpaRepository<Modele,Integer> {
    List<Modele> findByTypeMaterielId(Integer id);

    @Query("from Modele m where m.nom like :value% or m.typeMateriel.nom like :value% or m.marque.nom like :value%")
    List<Modele> findByNom(String value);
}
