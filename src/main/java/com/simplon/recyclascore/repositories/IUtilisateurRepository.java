package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
  Optional<Utilisateur> findByEmail(String email);
}
