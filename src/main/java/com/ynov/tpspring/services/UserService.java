package com.ynov.tpspring.services;

import com.ynov.tpspring.entities.Notification;
import com.ynov.tpspring.entities.Project;
import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Notification> getUserNotifications(Long userId) {
        return userRepository.getById(userId).getNotifications();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User '" + username + "' not found or inactive");
    }

    public List<Project> getUserParticipatingProjects(String name) {
        return userRepository.findByUsername(name).getParticipatingProjects();
    }

    public List<Project> getUserSubscribedProjects(String name) {
        return userRepository.findByUsername(name).getSubscribedProjects();
    }
}
