package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.dto.MailDTO;
import com.simplon.recyclascore.services.IServices.IMailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/mail")
public class MailController {

  private final IMailService mailService;

  @PostMapping("/send")
  public void sendMail(@RequestBody MailDTO mailDTO) throws MessagingException {
    this.mailService.sendMail(mailDTO);
  }
}
