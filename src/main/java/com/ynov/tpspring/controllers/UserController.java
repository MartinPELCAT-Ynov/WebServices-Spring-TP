package com.ynov.tpspring.controllers;

import com.ynov.tpspring.entities.Notification;
import com.ynov.tpspring.entities.Project;
import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> users() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PUT})
    public User createOrUpdateUser(@RequestBody User user) {
        return userService.createOrUpdateUser(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PreAuthorize("userService.getUserById(#userId).username == authentication.name")
    @RequestMapping(value = "/{userId}/notifications", method = RequestMethod.GET)
    public List<Notification> getUserNotifications(@PathVariable("userId") Long userId) {
        return userService.getUserNotifications(userId);
    }

    @RequestMapping(value = "/participating-projects", method = RequestMethod.GET)
    public List<Project> getUserParticipatingProjects(Principal principal) {
        return userService.getUserParticipatingProjects(principal.getName());
    }

    @RequestMapping(value = "/subscribed-projects", method = RequestMethod.GET)
    public List<Project> getUserSubscribedProjects(Principal principal) {
        return userService.getUserSubscribedProjects(principal.getName());
    }
}
