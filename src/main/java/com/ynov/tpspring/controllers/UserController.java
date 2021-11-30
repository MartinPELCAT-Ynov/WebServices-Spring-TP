package com.ynov.tpspring.controllers;

import com.ynov.tpspring.dto.CreateUserDTO;
import com.ynov.tpspring.entities.Notification;
import com.ynov.tpspring.entities.Project;
import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.services.AuthService;
import com.ynov.tpspring.services.UserService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> users(Principal principal) {
        User user = authService.whoami(principal);
        System.out.println(user.getUsername());
        return userService.getAllUsers();
    }

    @PreAuthorize("userRepository.findByUsername(user.username) == authentication.name")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public User updateUser(@RequestBody CreateUserDTO user, @RequestParam("centerUuid") String centerUuid) {
        return userService.createOrUpdateUser(user, centerUuid);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public List<Notification> getUserNotifications(Principal principal) {
        return userService.getUserNotifications(principal.getName());
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
