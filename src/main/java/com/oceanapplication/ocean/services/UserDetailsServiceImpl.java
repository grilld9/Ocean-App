package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.models.User;
import com.oceanapplication.ocean.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByPhoneNumber(username);
        return user
            .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
