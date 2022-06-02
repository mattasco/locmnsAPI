package com.mns.locmns.dao;

import com.mns.locmns.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmpruntDao extends JpaRepository<Emprunt,Integer> {
    Iterable<Emprunt> findByValideFalse();

    @Query("FROM Emprunt e where e.valide=true and e.dateRetourEffectif IS null")
    Iterable<Emprunt> findEnCours();

    @Query("FROM Emprunt e where e.dateRetourEffectif IS null and e.emprunteur.id=:id")
    Iterable<Emprunt> findEncoursById(@Param("id")Integer id);

    List<Emprunt> findByMaterielId(Integer id);
}
