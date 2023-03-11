package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.models.Account;
import com.oceanapplication.ocean.repo.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    private final AccountRepository accountRepository;
    public RegistrationService(AccountRepository accountRepository) {
      this.accountRepository = accountRepository;
    }

    public ResponseEntity<?> regNewAccount(String phoneNumber, String password) {
        Optional<Account> account = accountRepository.findByPhoneNumber(phoneNumber);
        if (account.isPresent()) {
            return new ResponseEntity<>("This phone number already exist", HttpStatus.BAD_REQUEST);
        }
        else {
            Account newAccount = new Account();
            newAccount.setPhoneNumber(phoneNumber);
            newAccount.setPassword(password);
            accountRepository.save(newAccount);
            return new ResponseEntity<>("Successful registration!", HttpStatus.OK);
        }
    }
}
