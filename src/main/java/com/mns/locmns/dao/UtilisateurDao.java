package com.mns.locmns.dao;

import com.mns.locmns.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurDao extends JpaRepository<Utilisateur,Integer> {
}
