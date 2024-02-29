package com.simplon.recyclascore.services;

import com.simplon.recyclascore.exception.JwtExpiredException;
import com.simplon.recyclascore.models.RefreshToken;
import com.simplon.recyclascore.models.Utilisateur;
import com.simplon.recyclascore.repositories.IUtilisateurRepository;
import com.simplon.recyclascore.repositories.IRefreshTokenRepository;
import com.simplon.recyclascore.services.IServices.IRefreshTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class RefreshTokenService implements IRefreshTokenService {

  private final IRefreshTokenRepository refreshTokenRepository;
  private final IUtilisateurRepository utilisateurRepository;

  @Override
  public RefreshToken createRefreshToken(String username, String token) {
    Instant now = Instant.now();
    Instant expirationTime = now.plus(120, ChronoUnit.MINUTES);
    Utilisateur utilisateur = this.utilisateurRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    this.refreshTokenRepository.findByUser(utilisateur).ifPresent(this.refreshTokenRepository::delete);

    log.info("Current tile", now);
    log.info("Expiration time", expirationTime);

    RefreshToken refreshToken = RefreshToken.builder()
      .user(utilisateur)
      .token(token)
      .expiryDate(Instant.now().plus(120, ChronoUnit.MINUTES))
      .build();

    return this.refreshTokenRepository.save(refreshToken);
  }

  @Override
  public Optional<RefreshToken> findByToken(String token) {
    RefreshToken refreshToken = this.refreshTokenRepository.findByToken(token).orElse(null);
    log.info("Refresh token", refreshToken);
    return this.refreshTokenRepository.findByToken(token);
  }

  @Override
  public RefreshToken verifyExpiration(RefreshToken refreshToken) throws JwtExpiredException {
    if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
      this.refreshTokenRepository.delete(refreshToken);
      throw new JwtExpiredException("Refresh token is expired. Please login again");
    }

    return refreshToken;
  }
}
