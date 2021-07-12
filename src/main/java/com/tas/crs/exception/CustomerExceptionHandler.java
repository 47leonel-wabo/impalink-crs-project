package com.tas.crs.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class CustomerExceptionHandler {

    private final MessageSource mMessageSource;

    @Autowired
    public CustomerExceptionHandler(MessageSource messageSource) {
        mMessageSource = messageSource;
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<CRSError> customerRequestBadRequest(
            final CustomerNotFoundException e,
            final HttpServletRequest request) {
        return new ResponseEntity<>(new CRSError(
                e.getLocalizedMessage(),
                LocalDateTime.now(),
                BAD_REQUEST.name(),
                request.getContextPath(),
                request.getLocale()
        ), BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CRSError> genericException(
            final HttpServletRequest request,
            final Exception e) {
        return new ResponseEntity<>(new CRSError(
                e.getLocalizedMessage(),
                LocalDateTime.now(),
                INTERNAL_SERVER_ERROR.name(),
                request.getContextPath()
                , request.getLocale()
        ), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<CRSError> messageNotFound(
            final HttpServletRequest request,
            final MessageNotFoundException e){
        return new ResponseEntity<>(new CRSError(
                e.getLocalizedMessage(),
                LocalDateTime.now(),
                BAD_REQUEST.name(),
                request.getContextPath()
                , request.getLocale()
        ), BAD_REQUEST);
    }
}
