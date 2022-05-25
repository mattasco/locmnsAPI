package com.mns.locmns.dao;

import com.mns.locmns.model.EtatMateriel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EtatMaterielDao extends JpaRepository<EtatMateriel,Integer> {
}
