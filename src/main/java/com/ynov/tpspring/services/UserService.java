package com.ynov.tpspring.services;

import com.ynov.tpspring.entities.Center;
import com.ynov.tpspring.entities.Notification;
import com.ynov.tpspring.entities.Project;
import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.repositories.CenterRepository;
import com.ynov.tpspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CenterRepository centerRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createOrUpdateUser(User user, String centerUuid) {
        Center center = centerRepository.findById(centerUuid).get();
        user.setCenter(center);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Notification> getUserNotifications(String username) {
        return userRepository.findByUsername(username).getNotifications();
    }

    public List<Project> getUserParticipatingProjects(String name) {
        return userRepository.findByUsername(name).getParticipatingProjects();
    }

    public List<Project> getUserSubscribedProjects(String name) {
        return userRepository.findByUsername(name).getSubscribedProjects();
    }
}
