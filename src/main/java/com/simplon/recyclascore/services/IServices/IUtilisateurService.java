package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.Utilisateur;
import jakarta.mail.MessagingException;

public interface IUtilisateurService {

  void createUtilisateur(Utilisateur utilisateur) throws MessagingException;

  Utilisateur loadUserByUsername(String username);
}
