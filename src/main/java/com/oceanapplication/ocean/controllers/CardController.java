package com.oceanapplication.ocean.controllers;

import com.oceanapplication.ocean.services.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCardId(@PathVariable Long userId) {
        return cardService.getCardId(userId);
    }

    @PostMapping("/{userId}")
    public String createNewCard(@PathVariable Long userId) {
        return cardService.createCard(userId);
    }
}
