package com.smtp.EmailNotification.services;

import com.smtp.EmailNotification.model.EmailInfo;

public interface EmailService {
    String sendWelcomeEmail(EmailInfo emailInfo);

    String sendProjectAssignedEmail(EmailInfo emailInfo);
}
