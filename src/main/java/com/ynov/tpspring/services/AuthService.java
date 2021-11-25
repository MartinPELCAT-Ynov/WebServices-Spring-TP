package com.ynov.tpspring.services;

import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;


    public User whoami(Principal principal) {
        return userRepository.findByUsername(principal.getName());
    }

    public User whoami(HttpServletRequest request) {
        return userRepository.findByUsername(request.getUserPrincipal().getName());
    }
}
