package com.oceanapplication.ocean.controllers;

import com.oceanapplication.ocean.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/card")
public class CardController {

    private final CardService cardService;

    @PostMapping("/{phoneNumber}")
    public ResponseEntity<String> getCardNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(cardService.getCardNumber(phoneNumber));
    }
}
