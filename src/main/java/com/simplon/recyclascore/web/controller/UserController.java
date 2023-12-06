package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.Utilisateur;
import com.simplon.recyclascore.repositories.IUtilisateurRepository;
import com.simplon.recyclascore.services.IServices.IUtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  private final IUtilisateurService utilisateurService;
  @PostMapping("/inscription")
  @ResponseStatus(HttpStatus.CREATED)
  public void createUtilisateur(@RequestBody Utilisateur utilisateur) {
    try {
      utilisateurService.createUtilisateur(utilisateur);
    }catch (Exception e) {
      log.warn(e.getMessage());
    }
  }
}
