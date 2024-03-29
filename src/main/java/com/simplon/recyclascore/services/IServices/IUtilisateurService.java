package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.Utilisateur;
import jakarta.mail.MessagingException;

public interface IUtilisateurService {

  void createUtilisateur(Utilisateur utilisateur) throws MessagingException;

  Utilisateur loadUserByUsername(String username);

  boolean actifCompte(String username);

  void forgotPassword(String email) throws MessagingException;
}
