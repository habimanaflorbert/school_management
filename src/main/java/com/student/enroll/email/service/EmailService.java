package com.student.enroll.email.service;

import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import java.nio.file.Paths;
import java.util.Map;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Async
    public CompletableFuture<Void> sendHtmlEmailAsync(String to, String subject, String templatePath, Map<String, String> placeholders) {
        return CompletableFuture.runAsync(() -> {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

                helper.setTo(to);
                helper.setSubject(subject);

                // Read HTML template content
                String htmlContent = Files.readString(Paths.get(templatePath));

                // Replace placeholders with actual values
                for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                    htmlContent = htmlContent.replace("{{" + entry.getKey() + "}}", entry.getValue());
                }

                // Set the HTML content
                helper.setText(htmlContent, true);

                mailSender.send(message);
            } catch (MessagingException | IOException e) {
                e.printStackTrace();
            }
        });}
}
