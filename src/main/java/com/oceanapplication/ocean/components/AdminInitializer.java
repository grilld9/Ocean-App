package com.oceanapplication.ocean.components;

import com.oceanapplication.ocean.models.Role;
import com.oceanapplication.ocean.models.User;
import com.oceanapplication.ocean.repo.RoleRepository;
import com.oceanapplication.ocean.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Order(2)
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByPhoneNumber("admin")) {
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(roleRepository.findByName("ROLE_USER"));
            adminRoles.add(roleRepository.findByName("ROLE_ADMIN"));
            User user = User.builder()
                    .phoneNumber("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles(adminRoles)
                    .build();
            userRepository.save(user);
        }
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
