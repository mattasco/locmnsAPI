package com.mns.locmns.dao;

import com.mns.locmns.model.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterielDao extends JpaRepository<Materiel,Integer> {
    List<Materiel> findByModeleId(Integer id);

    @Query(value = "SELECT * FROM materiel where materiel.modele_id=:id LIMIT 1", nativeQuery = true)
    Optional<Materiel> findOneByModeleId(Integer id);

    @Query("FROM Materiel materiel where materiel.numSerie like :value% or materiel.modele.nom like :value% or materiel.modele.marque.nom like :value% or materiel.modele.typeMateriel.nom like :value%")
    List<Materiel> findRecherche(String value);
}
