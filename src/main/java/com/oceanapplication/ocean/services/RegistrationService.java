package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.models.User;
import com.oceanapplication.ocean.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    public RegistrationService(UserRepository userRepository) {
      this.userRepository = userRepository;
    }

    public ResponseEntity<?> regNewAccount(String phoneNumber, String password) {
        Optional<User> account = userRepository.findByPhoneNumber(phoneNumber);
        if (account.isPresent()) {
            return new ResponseEntity<>("Phone number '" + phoneNumber + "' has already existed"
                    , HttpStatus.BAD_REQUEST);
        }
        else {
            User newUser = new User();
            newUser.setPhoneNumber(phoneNumber);
            newUser.setPassword(password);
            userRepository.save(newUser);
            return new ResponseEntity<>("Successful registration!", HttpStatus.OK);
        }
    }
}
