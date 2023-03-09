package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.models.Account;
import com.oceanapplication.ocean.repo.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

  private final AccountRepository accountRepository;
  public RegistrationService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account regNewAccount(String phoneNumber) {
    Account account = new Account();
    account.setPhoneNumber(phoneNumber);
    accountRepository.save(account);
    return account;
  }
}
