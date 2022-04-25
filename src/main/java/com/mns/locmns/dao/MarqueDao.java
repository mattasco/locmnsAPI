package com.mns.locmns.dao;

import com.mns.locmns.model.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarqueDao extends JpaRepository<Marque,Integer> {
}
