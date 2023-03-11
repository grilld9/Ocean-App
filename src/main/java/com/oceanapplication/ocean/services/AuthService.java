package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.models.Account;
import com.oceanapplication.ocean.repo.AccountRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final AccountRepository accountRepository;

    public AuthService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<?> auth(String phoneNumber, String password) {
        Optional<Account> account = accountRepository.findByPhoneNumber(phoneNumber);
        if (account.isPresent()) {
            if (account.get().getPassword().equals(password)) {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Wrong password!", HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<>("This user doesn't exist!", HttpStatus.BAD_REQUEST);
        }
    }
}
