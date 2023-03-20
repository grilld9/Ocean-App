package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.dto.PromoCreateRequestDTO;
import com.oceanapplication.ocean.models.Promo;
import com.oceanapplication.ocean.repo.PromoRepository;
import com.oceanapplication.ocean.utils.ImageUtil;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromoService {

    private final PromoRepository promoRepository;

    public String uploadImage(PromoCreateRequestDTO request) throws IOException {
        if (request.getHead().isEmpty()) {
            throw new IOException("Promo head is empty!");
        }
        if (request.getImage().isEmpty()) {
            throw new IOException("Image is absent!");
        }
        Promo imageData = promoRepository.save(Promo.builder()
            .imageData(ImageUtil.compressImage(request.getImage().getBytes()))
            .head(request.getHead())
            .body(request.getBody())
            .build());
            return "file uploaded successfully : " + request.getHead();
    }

    public byte[] downloadImage(String promoHead) throws IOException {
        var dbImageData = promoRepository.findByHead(promoHead).orElseThrow(()
                -> new IOException("Cannot find promo with head" + promoHead))
            .getImageData();
        return ImageUtil.decompressImage(dbImageData);
    }
}
