package com.oceanapplication.ocean.controllers;

import com.oceanapplication.ocean.dto.AuthRequestDTO;
import com.oceanapplication.ocean.dto.AuthResponseDTO;
import com.oceanapplication.ocean.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> logIn (@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(authService.auth(request));
    }
}

