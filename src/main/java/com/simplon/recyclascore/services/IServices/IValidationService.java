package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.Utilisateur;
import jakarta.mail.MessagingException;

public interface IValidationService {
  void enregistrer(Utilisateur utilisateur) throws MessagingException;

  void activateUtilisateur(String code);
}
