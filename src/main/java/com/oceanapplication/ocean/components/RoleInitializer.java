package com.oceanapplication.ocean.components;

import com.oceanapplication.ocean.models.Role;
import com.oceanapplication.ocean.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            Role adminRole = new Role("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }
        if (!roleRepository.existsByName("ROLE_USER")) {
            Role userRole = new Role("ROLE_USER");
            roleRepository.save(userRole);
        }
    }
}
