package com.ynov.tpspring.services;

import com.ynov.tpspring.config.security.JwtTokenProvider;
import com.ynov.tpspring.dto.TokenDTO;
import com.ynov.tpspring.entities.Center;
import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.repositories.CenterRepository;
import com.ynov.tpspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CenterRepository centerRepository;


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public User whoami(Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        return userRepository.findById(userId).get();
    }

    public User whoami(HttpServletRequest request) {
        return this.whoami(request.getUserPrincipal());
    }

    public TokenDTO getUserToken(String center_uuid, String username) {
        User user = userRepository.findByUsernameAndCenter(username, center_uuid);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            Center center = centerRepository.findById(center_uuid).get();
            user.setCenter(center);
            userRepository.save(user);
        }
        String token = jwtTokenProvider.createToken(user);
        return new TokenDTO(token);
    }
}
