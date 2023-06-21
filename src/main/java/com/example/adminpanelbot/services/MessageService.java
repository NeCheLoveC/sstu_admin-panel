package com.example.adminpanelbot.services;

import com.example.adminpanelbot.model.Message;
import com.example.adminpanelbot.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageService
{
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getLastFiveMessages()
    {
        return messageRepository.getLastFiveMessages();
    }

    public Message save(Message message)
    {
        return messageRepository.save(message);
    }
}
