package com.ynov.tpspring.services;

import com.ynov.tpspring.entities.Message;
import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.repositories.MessageRepository;
import com.ynov.tpspring.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Message createMessage(Long projectId, Message message, User user) {
        message.setProject(projectRepository.findById(projectId).get());
        message.setUser(user);
        return messageRepository.save(message);
    }

    public Message getMessage(Long messageId) {
        return messageRepository.findById(messageId).get();
    }
}
