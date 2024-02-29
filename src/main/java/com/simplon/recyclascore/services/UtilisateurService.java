package com.simplon.recyclascore.services;

import com.simplon.recyclascore.exception.InvalidEmailException;
import com.simplon.recyclascore.exception.InvalidPasswordException;
import com.simplon.recyclascore.models.Enum.EnumRole;
import com.simplon.recyclascore.models.Role;
import com.simplon.recyclascore.models.Utilisateur;
import com.simplon.recyclascore.repositories.IUtilisateurRepository;
import com.simplon.recyclascore.services.IServices.INotificationService;
import com.simplon.recyclascore.services.IServices.IUtilisateurService;
import com.simplon.recyclascore.services.IServices.IValidationService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service de gestion des utilisateurs
 */
@Service
@Slf4j
@AllArgsConstructor
public class UtilisateurService implements IUtilisateurService, UserDetailsService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final IUtilisateurRepository utilisateurRepository;
  private final IValidationService validationService;
  private final INotificationService notificationService;

  /**
   * Créer un utilisateur
   * @param utilisateur
   * @throws MessagingException
   */
  @Override
  public void createUtilisateur(Utilisateur utilisateur) throws MessagingException {
    if (!utilisateur.getEmail().contains("@")) {
      throw new InvalidEmailException("L'email doit contenir un @");
    }
    if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
      throw new InvalidEmailException("Cet email existe déjà");
    }
    if (utilisateur.getMotDePasse().isEmpty()) {
      throw new InvalidPasswordException("Le mot de passe ne peut pas être vide");
    }

    utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));

    Role roleUtilisateur = new Role();
    roleUtilisateur.setLibelle(EnumRole.USER);
    utilisateur.setRole(roleUtilisateur);

    utilisateur = utilisateurRepository.save(utilisateur);
    this.validationService.enregistrer(utilisateur);
  }

  /**
   * Charge un utilisateur par son email pour Spring Security
   * @param username
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public Utilisateur loadUserByUsername(String username) throws UsernameNotFoundException {
    log.warn("USER userService "+this.utilisateurRepository.findByEmail(username).toString());
    return utilisateurRepository.findByEmail(username)
      .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
  }

  /**
   * Vérifie si un utilisateur est actif
   * @param username
   * @return
   */
  @Override
  public boolean actifCompte(String username) {
    Utilisateur utilisateur = utilisateurRepository.findByEmail(username)
      .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
    log.warn("voici l'utilisateur : " + utilisateur.isActif());
    return utilisateur.isActif();
  }

  @Override
  public void forgotPassword(String email) throws MessagingException {
    try {
    Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
      .orElseThrow(() -> new InvalidEmailException("Cet email n'existe pas"));
//    if (utilisateur.) {
//      throw new InvalidEmailException("Cet email n'existe pas");
//    }
      this.notificationService.sendNotificationNewPassword(email);
    } catch (MessagingException e) {
      log.error("Erreur lors de l'envoi du mail de réinitialisation de mot de passe : {}", e.getMessage());
    }
  }
}
