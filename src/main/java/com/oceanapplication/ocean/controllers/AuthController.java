package com.oceanapplication.ocean.controllers;

import com.oceanapplication.ocean.services.AuthService;
import org.springframework.web.bind.annotation.*;
import com.oceanapplication.ocean.repo.AccountRepository;
import com.oceanapplication.ocean.models.Account;
import java.util.Optional;
import static java.util.Optional.empty;


@RestController
@RequestMapping(path="/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public Account logIn (@RequestBody String phoneNumber) {
        return authService.findByPhoneNumber(phoneNumber);
    }
}

