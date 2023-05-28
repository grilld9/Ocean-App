package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.dto.AuthRequestDTO;
import com.oceanapplication.ocean.dto.AuthResponseDTO;
import com.oceanapplication.ocean.models.Token;
import com.oceanapplication.ocean.models.TokenType;
import com.oceanapplication.ocean.models.User;
import com.oceanapplication.ocean.repo.TokenRepository;
import com.oceanapplication.ocean.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO auth(AuthRequestDTO request) {
        var user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(
                        () -> new UsernameNotFoundException("user not found " + request.getPhoneNumber()));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getPhoneNumber(),
                request.getPassword(),
                user.getAuthorities() // TODO test it
        ));
            var jwtToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthResponseDTO.builder()
                    .token(jwtToken)
                    .build();
    }

    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
