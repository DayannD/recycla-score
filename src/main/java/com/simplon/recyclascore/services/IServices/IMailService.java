package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.DTO.MailDTO;
import jakarta.mail.MessagingException;

public interface IMailService {
  void sendMail(MailDTO mailDTO) throws MessagingException;
}
