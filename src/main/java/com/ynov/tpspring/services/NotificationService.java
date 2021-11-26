package com.ynov.tpspring.services;

import com.ynov.tpspring.entities.Notification;
import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(Notification notification, User user) {
        notification.setUser(user);
        notificationRepository.save(notification);
    }

    public void sendNotification(Notification notification, List<User> users) {
        users.stream().forEach(user -> sendNotification(notification, user));
    }

}
