package com.mns.locmns.dao;

import com.mns.locmns.model.Modele;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ModeleDao extends JpaRepository<Modele,Integer> {
    List<Modele> findByTypeMaterielId(Integer id);
}
