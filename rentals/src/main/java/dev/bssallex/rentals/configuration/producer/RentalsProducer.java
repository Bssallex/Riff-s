package dev.bssallex.rentals.configuration.producer;

import dev.bssallex.rentals.configuration.EmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalsProducer {

    private final RabbitTemplate template;
    private final String routingKey = "email-queue";

    public void publishEvent(EmailDto dtoEmail){

        template.convertAndSend("", routingKey, dtoEmail);
    }

    // 3

}
