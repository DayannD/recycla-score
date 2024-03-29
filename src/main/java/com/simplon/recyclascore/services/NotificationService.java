package com.simplon.recyclascore.services;

import com.simplon.recyclascore.models.Validation;
import com.simplon.recyclascore.services.IServices.INotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
/**
 * Service de gestion des notifications
 */
@Service
@AllArgsConstructor
public class NotificationService implements INotificationService {
  private final JavaMailSender javaMailSender;

  /**
   * Envoie un mail de notification
   * @param validation
   * @throws MessagingException
   */
  @Override
  public void sendNotification(Validation validation) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

    helper.setFrom("no-reply@cjillo.etch");
    helper.setTo(validation.getUtilisateur().getEmail());
    helper.setSubject("Activation de votre compte");

    String htmlContent = "Bonjour " + validation.getUtilisateur().getUsername() + ",<br><br>" +
      "Pour activer votre compte, veuillez cliquer sur le lien ci-dessous :<br>" +
      "<a href='http://localhost:8080/activation?code=" + validation.getCode() + "'>ici</a><br><br>" +
      "Cordialement,<br>" +
      "L'équipe Recyclascore";

    helper.setText(htmlContent, true); // true signifie que le contenu est en HTML

    javaMailSender.send(mimeMessage);
  }

  public void sendNotificationNewPassword(String email) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

    helper.setFrom("no-reply@cjillo.etch");
    helper.setTo(email);
    helper.setSubject("Réinitialisation de votre mot de passe");

    String htmlContent = "Bonjour,<br><br>" +
      "Pour réinitialiser votre mot de passe, veuillez cliquer sur le lien ci-dessous :<br>" +
      "<a href='http://localhost:8080/reset-password?email=" + email + "'>ici</a><br><br>" +
      "Cordialement,<br>" +
      "L'équipe Recyclascore";

    helper.setText(htmlContent, true); // true signifie que le contenu est en HTML

    javaMailSender.send(mimeMessage);
  }
}
