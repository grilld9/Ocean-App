package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.models.MyUserDetails;
import com.oceanapplication.ocean.models.User;
import com.oceanapplication.ocean.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(username).orElseThrow(
                () -> new UsernameNotFoundException("user not found " + username));
        return new MyUserDetails(user);
    }
}
