package com.simplon.recyclascore.services;

import com.simplon.recyclascore.models.dto.MailDTO;
import com.simplon.recyclascore.services.IServices.IMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService implements IMailService {
  private final JavaMailSender javaMailSender;

  @Override
  public void sendMail(MailDTO mailDTO) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

    helper.setFrom(mailDTO.from());
    helper.setTo("no-reply@cjillo.etch");
    helper.setSubject(mailDTO.object());

    String htmlContent = mailDTO.message();

    helper.setText(htmlContent, true); // true signifie que le contenu est en HTML

    javaMailSender.send(mimeMessage);
  }

}
