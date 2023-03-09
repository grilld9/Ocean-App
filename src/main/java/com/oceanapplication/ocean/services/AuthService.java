package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.models.Account;
import com.oceanapplication.ocean.repo.AccountRepository;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthService {

  private final AccountRepository accountRepository;

  public AuthService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account findByPhoneNumber(String phoneNumber) {
    return accountRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
            new NoSuchElementException("USER with phone number='" + phoneNumber + "' does not exist"));
  }
}
