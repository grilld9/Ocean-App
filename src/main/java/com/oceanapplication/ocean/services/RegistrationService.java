package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.dto.AuthResponseDTO;
import com.oceanapplication.ocean.dto.RegistrationRequestDTO;
import com.oceanapplication.ocean.models.Role;
import com.oceanapplication.ocean.models.User;
import com.oceanapplication.ocean.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public AuthResponseDTO register(RegistrationRequestDTO request) {
        var user = User.builder()
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        authService.saveUserToken(savedUser, jwtToken);
        return AuthResponseDTO.builder()
                .token(jwtToken)
                .build();
    }
}
