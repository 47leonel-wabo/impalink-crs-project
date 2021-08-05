package com.tas.crs.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailSender {

    private final JavaMailSender mMailSender;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        mMailSender = mailSender;
    }

    @Async
    @Override
    public void send(String to, String message) {
        MimeMessage mimeMessage = mMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        try {
            mimeMessageHelper.setText(message, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("Confirm account creation");
            mimeMessageHelper.setFrom("mail@team-solutions.net");
            mMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("Unable to send email", e);
            throw new IllegalStateException("Unable to send email");
        }
    }
}
