package com.smtp.EmailNotification.services;

import com.smtp.EmailNotification.model.EmailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    @Override
    public String sendWelcomeEmail(EmailInfo emailInfo) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(emailInfo.getEmail());
            message.setSentDate(new Date());
            message.setSubject(emailInfo.getSubject());
            message.setText(emailInfo.getMsgBody());

            javaMailSender.send(message);
            return "success";
        }catch (Exception e){
            return "mail not sent";
        }
    }

    @Override
    public String sendProjectAssignedEmail(EmailInfo emailInfo) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setFrom(emailInfo.getEmail_adm());
            message.setTo(emailInfo.getEmail());
            message.setSentDate(new Date());
            message.setSubject(emailInfo.getSubject());
            message.setText(emailInfo.getMsgBody());

            javaMailSender.send(message);
            return "success";
        }catch (Exception e){
            return "mail not sent";
        }
    }
}
