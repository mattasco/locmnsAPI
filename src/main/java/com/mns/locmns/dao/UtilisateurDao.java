package com.mns.locmns.dao;

import com.mns.locmns.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findByAdminTrue();

    Optional<Utilisateur> findByLogin(String login);

    @Query("from Utilisateur u where u.nom like :value% or u.prenom like :value%")
    List<Utilisateur> findByNomOrPrenom(String value);
}
