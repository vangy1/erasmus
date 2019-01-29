package com.erasmus.authentication;

import com.erasmus.db.model.Admin;
import com.erasmus.db.model.User;
import com.erasmus.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticationType getAuthentication() {
        if (!isAuthenticated()) return AuthenticationType.GUEST;
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user instanceof Admin) {
            return AuthenticationType.ADMIN;
        } else {
            return AuthenticationType.VOTER;
        }
    }

    private boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public User getLoggedAccount() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User loggedAccount = userRepository.findByUsername(principal.getName());
        if (loggedAccount == null) {
            throw new UnauthorizedException();
        }
        return loggedAccount;
    }
}
