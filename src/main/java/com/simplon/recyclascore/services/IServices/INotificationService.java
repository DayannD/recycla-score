package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.Validation;
import jakarta.mail.MessagingException;

public interface INotificationService {
  public void sendNotification(Validation validation) throws MessagingException;

  void sendNotificationNewPassword(String email) throws MessagingException;
}
