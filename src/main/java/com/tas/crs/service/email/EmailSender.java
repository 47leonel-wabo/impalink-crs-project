package com.tas.crs.service.email;

import javax.mail.MessagingException;

public interface EmailSender {
    void send(final String to, final String message) throws MessagingException;
}
