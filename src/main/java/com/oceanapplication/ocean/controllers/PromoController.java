package com.oceanapplication.ocean.controllers;

import com.oceanapplication.ocean.dto.PromoCreateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/promo")
public class PromoController {

    private final PromoService promoService;
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PromoCreateRequestDTO request) {
        return ResponseEntity.ok(promoService.createPost(request));
    }
}
