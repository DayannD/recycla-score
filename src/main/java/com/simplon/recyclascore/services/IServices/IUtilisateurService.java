package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.Utilisateur;
import jakarta.mail.MessagingException;

public interface IUtilisateurService {

  public void createUtilisateur(Utilisateur utilisateur) throws MessagingException;
}
