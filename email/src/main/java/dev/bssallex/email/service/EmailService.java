package dev.bssallex.email.service;

import dev.bssallex.email.entity.Email;
import dev.bssallex.email.enums.EmailStatus;
import dev.bssallex.email.exceptions.SendEmailFailed;
import dev.bssallex.email.repository.EmailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

     private final JavaMailSender mailSender;
     private final EmailRepository repository;

     @Value("${spring.mail.username}")
     public String emailFrom;

     @Transactional
     public void sendEmail(Email email){
         try {
             SimpleMailMessage message = new SimpleMailMessage();
             message.setTo(email.getEmailTo());
             message.setText(email.getBody());
             email.setEmailFrom(emailFrom);
             email.getEmailSubject();
             email.getStatusEmail();
             email.getSendDateEmail();

             mailSender.send(message);
         } catch (Exception e) {
             email.setStatusEmail(EmailStatus.FAILED);
             throw new SendEmailFailed("Erro ao enviar o email");
         }
         repository.save(email);
     }
}
