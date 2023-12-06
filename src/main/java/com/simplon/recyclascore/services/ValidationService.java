package com.simplon.recyclascore.services;

import com.simplon.recyclascore.models.Utilisateur;
import com.simplon.recyclascore.models.Validation;
import com.simplon.recyclascore.repositories.IValidationRepository;
import com.simplon.recyclascore.services.IServices.INotificationService;
import com.simplon.recyclascore.services.IServices.IValidationService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
@AllArgsConstructor
public class ValidationService implements IValidationService {

  private IValidationRepository validationRepository;
  private INotificationService notificationService;

  @Override
  public void enregistrer(Utilisateur utilisateur) throws MessagingException {
    Validation validation = new Validation();
    validation.setUtilisateur(utilisateur);
    Instant creation = Instant.now();
    validation.setCreationDate(creation);

    Instant expire = creation.plusSeconds(3600);
    validation.setExpire(expire);

    Random random = new Random();
    random.nextInt(999999);
    String code = String.format("%06d", random.nextInt(999999));
    validation.setCode(code);

    validationRepository.save(validation);
    notificationService.sendNotification(validation);
  }
}
