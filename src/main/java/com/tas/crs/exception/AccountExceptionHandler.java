package com.tas.crs.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class AccountExceptionHandler {

    private final MessageSource mMessageSource;

    @Autowired

    public AccountExceptionHandler(MessageSource mMessageSource) {
        this.mMessageSource = mMessageSource;
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<AccountException> accountRequestBadRequest (
            final AccountNotFoundException e,
            final HttpServletRequest request) {
        return new ResponseEntity<>(new AccountException(
                e.getLocalizedMessage(),
                LocalDateTime.now(),
                BAD_REQUEST.name(),
                request.getContextPath(),
                request.getLocale()
        ), BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AccountException> genericException (
            final HttpServletRequest request,
            final Exception e) {
        return new ResponseEntity<>(new AccountException(
                e.getLocalizedMessage(),
                LocalDateTime.now(),
                INTERNAL_SERVER_ERROR.name(),
                request.getContextPath(),
                request.getLocale()
        ), INTERNAL_SERVER_ERROR);
    }
}
