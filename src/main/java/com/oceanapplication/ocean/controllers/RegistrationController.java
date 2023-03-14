package com.oceanapplication.ocean.controllers;

import com.oceanapplication.ocean.dto.AuthResponseDTO;
import com.oceanapplication.ocean.dto.RegistrationRequestDTO;
import com.oceanapplication.ocean.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/reg")
@RequiredArgsConstructor
public class RegistrationController {

  private final RegistrationService registrationService;

  @PostMapping
  public ResponseEntity<AuthResponseDTO> register(@RequestBody RegistrationRequestDTO request) {
      return ResponseEntity.ok(registrationService.register(request));
  }
}
