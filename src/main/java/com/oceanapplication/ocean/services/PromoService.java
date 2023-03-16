package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.dto.PromoCreateRequestDTO;
import com.oceanapplication.ocean.models.Promo;
import com.oceanapplication.ocean.repo.PromoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromoService {

    private final PromoRepository promoRepository;

    public String createPromo(PromoCreateRequestDTO request) {
        var promo = Promo.builder().
    }
}
