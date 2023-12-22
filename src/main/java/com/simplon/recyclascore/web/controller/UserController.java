package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.DTO.ConnexionUserDTO;
import com.simplon.recyclascore.config.security.JwtService;
import com.simplon.recyclascore.exception.InvalidCodeException;
import com.simplon.recyclascore.models.Utilisateur;
import com.simplon.recyclascore.services.IServices.IUtilisateurService;
import com.simplon.recyclascore.services.IServices.IValidationService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

  /**
   * @param utilisateur
   * @PostMapping("/inscription") : crée un utilisateur
   */
  @PostMapping("/inscription")
  @ResponseStatus(HttpStatus.CREATED)
  public void createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
    try {
      utilisateurService.createUtilisateur(utilisateur);
    }catch (Exception e) {
      log.warn(e.getMessage());
    }
  }

  /**
   * @param code
   * @return ResponseEntity<String>
   * @GetMapping("/activation") : active un utilisateur
   */
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


  /**
   * @param connexionUserDTO
   * @param response
   * @return ResponseEntity<String>
   * @PostMapping("/connexion") : connecte un utilisateur et lui donne un token
   */
  @PostMapping("/connexion")
  public ResponseEntity<String> connexionUtilisateur(@RequestBody ConnexionUserDTO connexionUserDTO, HttpServletResponse response) {
    final Authentication authenticate = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(connexionUserDTO.username(), connexionUserDTO.password())
    );
    if (authenticate.isAuthenticated()) {
      if (!utilisateurService.actifCompte(connexionUserDTO.username())) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Votre compte n'est pas actif");
      }
      Map<String,String> token = jwtService.createToken(connexionUserDTO.username());

      log.info(token.toString());
      Cookie cookie = new Cookie("token", token.get("Bearer"));
      // a mettre quand on sera en https
      //cookie.setHttpOnly(true);
      //cookie.setSecure(true);
      cookie.setPath("/");
      response.addCookie(cookie);
      log.info(cookie.toString());
      return ResponseEntity.ok("Vous êtes connecté");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
    }
  }
}
