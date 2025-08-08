package com.gpcodefusion.assetmanager.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendResetEmail(String toEmail, String resetUrl) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Reset Password - Asset Management");

            String content = "<p>Hello,</p>"
                    + "<p>Click the link below to reset your password:</p>"
                    + "<a href=\"" + resetUrl + "\" style=\"color: blue;\">Reset Password</a>"
                    + "<p>If you didn’t request this, you can ignore this email.</p>";

            helper.setText(content, true); // true = send HTML

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // or use proper logging
        }
    }
}
