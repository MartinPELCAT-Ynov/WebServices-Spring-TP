package com.ynov.tpspring.services;

import com.ynov.tpspring.entities.*;
import com.ynov.tpspring.repositories.MessageRepository;
import com.ynov.tpspring.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private NotificationService notificationService;

    public Message createMessage(Long projectId, Message message, User user) {
        Project project = projectRepository.findById(projectId).get();
        message.setProject(project);
        message.setUser(user);
        messageRepository.save(message);
        Notification notification = new Notification("New message in the project" + project.getName(), NotificationType.NEW_MESSAGE);
        notificationService.sendNotification(notification, project.getSubscribers());
        return message;
    }

    public Message getMessage(Long messageId) {
        return messageRepository.findById(messageId).get();
    }
}
