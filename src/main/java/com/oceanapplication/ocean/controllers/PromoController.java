package com.oceanapplication.ocean.controllers;

import com.oceanapplication.ocean.dto.PromoCreateRequestDTO;
import com.oceanapplication.ocean.services.PromoService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/promo")
public class PromoController {

    private final PromoService promoService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,
        @RequestParam("head") String head, @RequestParam("body") String body) throws IOException {
        PromoCreateRequestDTO request = PromoCreateRequestDTO.builder()
            .image(file)
            .head(head)
            .body(body)
            .build();
        String uploadImage = promoService.uploadImage(request);
        return ResponseEntity.ok(uploadImage);
    }

    @GetMapping("/{head}")
    public ResponseEntity<?> downloadImage(@PathVariable String head) throws IOException {
        byte[] imageData = promoService.downloadImage(head);
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.valueOf("image/png"))
            .body(imageData);

    }
}
