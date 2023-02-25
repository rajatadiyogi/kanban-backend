package com.smtp.EmailNotification.rabbitmq;

import com.smtp.EmailNotification.model.EmailInfo;
import com.smtp.EmailNotification.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "email-welcome")
    public void getDataFromQueue(EmailInfo emailInfo){
        String result = emailService.sendWelcomeEmail(emailInfo);
        System.out.println(result);
    }

    @RabbitListener(queues = "assigning_project")
    public void ProjectAssigned(EmailInfo emailInfo){
        String result = emailService.sendWelcomeEmail(emailInfo);
        System.out.println(result);
    }
}
