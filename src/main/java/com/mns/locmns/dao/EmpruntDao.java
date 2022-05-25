package com.mns.locmns.dao;

import com.mns.locmns.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmpruntDao extends JpaRepository<Emprunt,Integer> {
    Optional<Emprunt> findByMaterielId(Integer materielId);
}
