package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.models.User;
import com.oceanapplication.ocean.repo.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> auth(String phoneNumber, String password) {
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
    }
}
