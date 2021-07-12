package com.tas.crs.service;

import com.tas.crs.entity.MessageObject;
import com.tas.crs.repository.MessageObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageObjectRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageObjectRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageObject addMessage(final MessageObject messageObject) {
        if (messageObject.getDateTime() == null) {
            return messageRepository.save(new MessageObject(
                    messageObject.getFrom(),
                    messageObject.getTo(),
                    messageObject.getTitle(),
                    messageObject.getMessage(),
                    LocalDateTime.now()
            ));
        }
        return messageRepository.save(messageObject);
    }

    @Override
    public Optional<MessageObject> getMessage(final Long messageId) {
        return messageRepository.findById(messageId);
    }

    @Override
    public Iterable<MessageObject> getMessages() {
        return messageRepository.findAll();
    }

    // TODO: Define relationship between Message - Customer - CRS representative
}
