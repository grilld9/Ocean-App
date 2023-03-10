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

    public Long getCardId(Long id) {
        return accountRepository.findById(id).orElseThrow(() ->
            new NoSuchElementException("USER with id='" + id + "' does not exist"))
            .getCardId(); // мб переделать
    }
}
