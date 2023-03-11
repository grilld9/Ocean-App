package com.oceanapplication.ocean.controllers;

import com.oceanapplication.ocean.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<?> logIn (@RequestBody String phoneNumber, @RequestBody String password) {
        return authService.auth(phoneNumber, password);
    }
}

