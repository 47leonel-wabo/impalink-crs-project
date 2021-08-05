package com.tas.crs.dto;

public class EmailDto {
    private final String destination;
    private final String messageContent;

    public EmailDto(String destination, String messageContent) {
        this.destination = destination;
        this.messageContent = messageContent;
    }

    public String getDestination() {
        return destination;
    }

    public String getMessageContent() {
        return messageContent;
    }
}
