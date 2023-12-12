package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.DTO.ConnexionUserDTO;
import com.simplon.recyclascore.config.security.JwtService;
import com.simplon.recyclascore.exception.InvalidCodeException;
import com.simplon.recyclascore.models.Utilisateur;
import com.simplon.recyclascore.services.IServices.IUtilisateurService;
import com.simplon.recyclascore.services.IServices.IValidationService;
import java.util.Collections;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
public class UserController {

  private final IUtilisateurService utilisateurService;
  private final IValidationService validationService;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  
  @PostMapping("/inscription")
  @ResponseStatus(HttpStatus.CREATED)
  public void createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
    try {
      utilisateurService.createUtilisateur(utilisateur);
    }catch (Exception e) {
      log.warn(e.getMessage());
    }
  }

  @GetMapping("/activation")
  public ResponseEntity<String> activateUtilisateur(@RequestParam("code") String code) {
    try {
      validationService.activateUtilisateur(code);
      return ResponseEntity.ok("Votre compte à était activer");
    }catch (InvalidCodeException e) {
      log.warn("Échec de l'activation: {}", e.getMessage());
      return ResponseEntity.badRequest().body("Échec de l'activation : " + e.getMessage());
    } catch (Exception e) {
      log.error("Erreur inattendue lors de l'activation: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Une erreur interne est survenue.");
    }
  }

  @PostMapping("/connexion")
  public Map<String, String> connexionUtilisateur(@RequestBody ConnexionUserDTO connexionUserDTO) {
    final Authentication authenticate = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(connexionUserDTO.username(), connexionUserDTO.password())
    );
    if (authenticate.isAuthenticated()) {
      return jwtService.createToken(connexionUserDTO.username());
    } else {
      return Collections.emptyMap();
    }
  }
}
