package com.example.bookplace.filter;

import com.example.bookplace.models.User;
import com.example.bookplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(),
                user.get().getPassword(),
                Collections.emptyList()
        );
    }
}
