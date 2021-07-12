package com.tas.crs.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class MessageObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // TODO: Define relationships
    @Column(name = "from", nullable = false, updatable = false)
    private final String from;

    @Column(name = "to", nullable = false, updatable = false)
    private final String to;

    @Column(name = "title", nullable = false, updatable = false)
    private final String title;

    @Column(name = "message", nullable = false, updatable = false)
    private final String message;

    @Column(name = "date_time", nullable = false, updatable = false)
    private LocalDateTime dateTime;

    public MessageObject(String from,
                         String to,
                         String title,
                         String message) {
        this.from = from;
        this.to = to;
        this.title = title;
        this.message = message;
    }

    public MessageObject(String from,
                         String to,
                         String title,
                         String message,
                         LocalDateTime dateTime) {
        this.from = from;
        this.to = to;
        this.title = title;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
