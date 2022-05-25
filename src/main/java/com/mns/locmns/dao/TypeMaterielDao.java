package com.mns.locmns.dao;

import com.mns.locmns.model.TypeMateriel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TypeMaterielDao extends JpaRepository<TypeMateriel,Integer> {
}
