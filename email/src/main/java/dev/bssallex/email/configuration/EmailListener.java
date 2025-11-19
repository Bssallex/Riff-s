package dev.bssallex.email.configuration;

import dev.bssallex.email.dto.EmailDto;
import dev.bssallex.email.entity.Email;
import dev.bssallex.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailListener {

    private final EmailService emailService;

    @RabbitListener(queues = "email-queue")
    public void listenerEmailQueue(@Payload EmailDto emailDto){

        var email = new Email();
        BeanUtils.copyProperties(emailDto, email);

        emailService.sendEmail(email);
    }

    // 2


}
