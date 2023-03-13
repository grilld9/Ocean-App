package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.models.User;
import com.oceanapplication.ocean.repo.UserRepository;

import java.util.Optional;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final UserRepository userRepository;

    public CardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getCardId(Long userId) {
        Optional<User> account = userRepository.findById(userId);
        if (account.get().getCardId() != null) {
            return new ResponseEntity<>(account.get().getCardId(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Card haven't generated yet", HttpStatus.BAD_REQUEST);
        }
    }

    public String createCard(Long userId) {
        Optional<User> account = userRepository.findById(userId);
        String generatedCardId = generateNewCardId();
        account.get().setCardId(generatedCardId);
        return generatedCardId;
    }

    /**
     * Test version of generating barcode.
     * @return random generated value
     */
    public String generateNewCardId() {
        return Long.toString(new Random().nextLong(1000000000000L,10000000000000L));
    }
}
