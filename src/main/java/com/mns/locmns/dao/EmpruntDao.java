package com.mns.locmns.dao;

import com.mns.locmns.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpruntDao extends JpaRepository<Emprunt,Integer> {
    Optional<Emprunt> findByMaterielId(Integer materielId);
}
