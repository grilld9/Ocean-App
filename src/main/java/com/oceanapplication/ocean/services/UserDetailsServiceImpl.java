package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.models.MyUserDetails;
import com.oceanapplication.ocean.models.User;
import com.oceanapplication.ocean.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipal;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(username).orElseThrow(
                () -> new UsernameNotFoundException("user not found " + username));
        return new MyUserDetails(user);
    }
}
