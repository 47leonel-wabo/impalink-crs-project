package com.tas.crs.exception;

import java.time.LocalDateTime;
import java.util.Locale;

public class AccountException {
    private final String message;
    private final LocalDateTime date;
    private final String code;
    private final String path;
    private final Locale local;

    public AccountException(String message, LocalDateTime date, String code, String path, Locale local) {
        this.message = message;
        this.date = date;
        this.code = code;
        this.path = path;
        this.local = local;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public Locale getLocal() {
        return local;
    }
}
