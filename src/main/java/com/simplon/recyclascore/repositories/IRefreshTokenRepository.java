package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.RefreshToken;
import com.simplon.recyclascore.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

  Optional<RefreshToken> findByUser(Utilisateur user);
}
