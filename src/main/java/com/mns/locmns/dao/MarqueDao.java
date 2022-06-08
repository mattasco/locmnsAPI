package com.mns.locmns.dao;

import com.mns.locmns.model.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MarqueDao extends JpaRepository<Marque,Integer> {
    @Query("from Marque u where u.nom like :value%")
    public List<Marque> findByNomLike(String value);

    public Marque findByNom(String nom);
}
