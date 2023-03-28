package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.dto.AuthResponseDTO;
import com.oceanapplication.ocean.dto.RegistrationRequestDTO;
import com.oceanapplication.ocean.models.Role;
import com.oceanapplication.ocean.models.User;
import com.oceanapplication.ocean.repo.RoleRepository;
import com.oceanapplication.ocean.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final RoleRepository roleRepository;

    public AuthResponseDTO register(RegistrationRequestDTO request) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER")); //TODO test it
        var user = User.builder()
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        authService.saveUserToken(savedUser, jwtToken);
        return AuthResponseDTO.builder()
                .token(jwtToken)
                .build();
    }
}
