package com.simplon.recyclascore.services;

import com.simplon.recyclascore.models.Enum.EnumRole;
import com.simplon.recyclascore.models.Role;
import com.simplon.recyclascore.models.Utilisateur;
import com.simplon.recyclascore.repositories.IUtilisateurRepository;
import com.simplon.recyclascore.services.IServices.IUtilisateurService;
import com.simplon.recyclascore.services.IServices.IValidationService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UtilisateurService implements IUtilisateurService {

  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private IUtilisateurRepository utilisateurRepository;
  private IValidationService validationService;

  @Override
  public void createUtilisateur(Utilisateur utilisateur) throws MessagingException {
    if (!utilisateur.getEmail().contains("@")) {
      throw new RuntimeException("L'emalil doit contenir un @");
    }
    if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
      throw new RuntimeException("Cet email existe déjà");
    }
    if (utilisateur.getMotDePasse().isEmpty()) {
      throw new RuntimeException("Le mot de passe ne peut pas être vide");
    }

    utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));

    Role roleUtilisateur = new Role();
    roleUtilisateur.setLibelle(EnumRole.USER);
    utilisateur.setRole(roleUtilisateur);

    utilisateur = utilisateurRepository.save(utilisateur);
    this.validationService.enregistrer(utilisateur);
  }
}
