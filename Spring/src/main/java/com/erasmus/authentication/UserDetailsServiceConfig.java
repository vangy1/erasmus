package com.erasmus.authentication;

import com.erasmus.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceConfig implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.erasmus.db.model.User user = this.userRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}