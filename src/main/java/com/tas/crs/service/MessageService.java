package com.tas.crs.service;

import com.tas.crs.entity.MessageObject;

import java.util.Optional;

public interface MessageService {
    MessageObject addMessage(MessageObject messageObject);

    Optional<MessageObject> getMessage(Long messageId);

    Iterable<MessageObject> getMessages();
}
