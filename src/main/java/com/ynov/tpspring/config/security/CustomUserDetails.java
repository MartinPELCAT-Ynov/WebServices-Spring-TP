package com.ynov.tpspring.config.security;

import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .authorities(new ArrayList<>())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }


    public UserDetails loadUserByUserNameAndCenter(String username, String centerUuid) throws UsernameNotFoundException {
        final User user = userRepository.findByUsernameAndCenter(username, centerUuid);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' in center '" + centerUuid + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getId().toString())
                .password("")
                .authorities(new ArrayList<>())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}