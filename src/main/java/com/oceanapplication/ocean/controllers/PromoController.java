package com.oceanapplication.ocean.controllers;

import com.oceanapplication.ocean.dto.PromoCreateRequestDTO;
import com.oceanapplication.ocean.dto.PromoResponseDTO;
import com.oceanapplication.ocean.services.PromoService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private PromoService promoService;

    @PostMapping
    public ResponseEntity<String> addNewPost(@RequestParam("image") MultipartFile file,
        @RequestParam("head") String head, @RequestParam("body") String body) throws IOException {
        PromoCreateRequestDTO request = PromoCreateRequestDTO.builder()
            .image(file)
            .head(head)
            .body(body)
            .build();
        promoService.addNewPost(request);
        return ResponseEntity.ok("Post added " + head);
    }

    @GetMapping("/{head}")
    public ResponseEntity<?> downloadImage(@PathVariable String head) throws IOException {
        return ResponseEntity.ok()
            .contentType(MediaType.valueOf("image/png"))
            .body(promoService.getPost(head));
    }

    @GetMapping("/show")
    public ResponseEntity<List<PromoResponseDTO>> getAllActivePromos() {
        return ResponseEntity.ok(promoService.getAllActivePromos());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/activate/{head}")
    private ResponseEntity<String> activatePromo(@PathVariable String head) {
        promoService.activatePromo(head);
        return ResponseEntity.ok("Successful promo activation!" + head);
    }

    @PostMapping("/deactivate/{head}")
    private ResponseEntity<String> deactivatePromo(@PathVariable String head) {
        promoService.deactivatePromo(head);
        return ResponseEntity.ok("Successful promo deactivation!" + head);
    }
}
