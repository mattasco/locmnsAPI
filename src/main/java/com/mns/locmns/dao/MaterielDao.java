package com.mns.locmns.dao;

import com.mns.locmns.model.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterielDao extends JpaRepository<Materiel,Integer> {
}
