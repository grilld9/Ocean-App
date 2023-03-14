package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.models.User;
import com.oceanapplication.ocean.repo.TokenRepository;
import com.oceanapplication.ocean.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public
    /*public ResponseEntity<?> auth(String phoneNumber, String password) {
        Optional<User> account = userRepository.findByPhoneNumber(phoneNumber);
        if (account.isPresent()) {
            if (account.get().getPassword().equals(password)) {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Wrong password!", HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<>("User with phone number '" + phoneNumber + "' doesn't exist!"
                    , HttpStatus.BAD_REQUEST);
        }
    }*/
}
