package com.oceanapplication.ocean.controllers;

import com.oceanapplication.ocean.services.CardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{id}")
    public Long getCardId(@PathVariable Long id) {
        return cardService.getCardId(id);
    }
}
