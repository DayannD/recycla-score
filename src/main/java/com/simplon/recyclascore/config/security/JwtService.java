package com.simplon.recyclascore.config.security;

import com.simplon.recyclascore.models.Utilisateur;
import com.simplon.recyclascore.services.IServices.IUtilisateurService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JwtService {

  private final String ENCRIPTION_KEY = "f1ebe03f9cfef5dfb10564644a263dee59659c39b0d42d8b3bfdffb82486f4f5";
  private final IUtilisateurService utilisateurService;
  public Map<String, String> createToken(String username) {
    Utilisateur utilisateur = this.utilisateurService.loadUserByUsername(username);
    return this.generateToken(utilisateur);
  }

  private Map<String, String> generateToken(Utilisateur utilisateur) {
    final long currentTime = System.currentTimeMillis();
    final long expirationTime = currentTime + 1000 * 60 * 30;

    final Map<String, Object> claims = Map.of(
      "token", "token",
      "role", utilisateur.getRole().getLibelle().toString(),
      "nom", utilisateur.getNomUtilisateur(),
      Claims.EXPIRATION, new Date(expirationTime),
      Claims.SUBJECT, utilisateur.getEmail()
    );


    final String bearer = Jwts.builder()
      .setIssuedAt(new Date(currentTime))
      .setExpiration(new Date(expirationTime))
      .setSubject(utilisateur.getEmail())
      .setClaims(claims)
      .signWith(getKey(), SignatureAlgorithm.HS256)
      .compact();

    return Map.of("Bearer", bearer);
  }

  private Key getKey() {
    final byte[] decoder = Decoders.BASE64.decode(ENCRIPTION_KEY);
    return Keys.hmacShaKeyFor(decoder);
  }

  public String lireUsername(String token) {
    return this.getClaim(token, Claims::getSubject);
  }

  public boolean isTokenExpired(String token) {
    Date expiration = getExpirationDate(token);
    return expiration.before(new Date());
  }

  private Date getExpirationDate(String token) {
    return this.getClaim(token, Claims::getExpiration);
  }

  private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = this.getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaims(String token) {
    return Jwts.parser()
      .setSigningKey(this.getKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }
}
