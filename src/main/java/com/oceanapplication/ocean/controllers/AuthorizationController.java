package com.oceanapplication.ocean.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.oceanapplication.ocean.repo.AccountRepository;
import com.oceanapplication.ocean.models.Account;

import java.util.Optional;


@Controller
@RequestMapping(path="/demo")
public class AuthorizationController {
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String phoneNumber) {

        Account n = new Account();
        n.setName(name);
        n.setPhoneNumber(phoneNumber);
        accountRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Account> getAllUsers() {
        // This returns a JSON or XML with the users
        return accountRepository.findAll();
    }

    @PostMapping(path="/verify")
    public @ResponseBody Optional<Account> findAccount(@RequestParam String phoneNumber) {
        return accountRepository.findByPhoneNumber(phoneNumber);
    }
}

