package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.repo.AccountRepository;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final AccountRepository accountRepository;

    public CardService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Long getCardId(Long userId) {
        return accountRepository.findById(userId).orElseThrow(() ->
            new NoSuchElementException("USER with id='" + userId + "' does not exist"))
            .getCardId(); // мб переделать
    }
}
