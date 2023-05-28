package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.dto.PromoCreateRequestDTO;
import com.oceanapplication.ocean.dto.PromoResponseDTO;
import com.oceanapplication.ocean.models.Promo;
import com.oceanapplication.ocean.repo.PromoRepository;
import com.oceanapplication.ocean.utils.ImageUtil;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PromoService {

    private static final int PAGE_SIZE = 20;

    private final PromoRepository promoRepository;

    public void addNewPost(PromoCreateRequestDTO request) throws IOException {
        if (request.getHead().isEmpty()) {
            throw new IOException("Promo head is empty!");
        }
        if (request.getImage().isEmpty()) {
            throw new IOException("Image is absent!");
        }
        Promo promo = promoRepository.save(Promo.builder()
            .imageData(ImageUtil.compressImage(request.getImage().getBytes()))
            .head(request.getHead())
            .body(request.getBody())
            .active(false)
            .build());
    }

    /*@Transactional(readOnly = true)
    public byte[] getPost(String promoHead) throws IOException {
        var dbImageData = promoRepository.findByHead(promoHead).orElseThrow(()
                -> new IOException("Cannot find promo with head" + promoHead))
            .getImageData();
        return ImageUtil.decompressImage(dbImageData);
    }*/

    @Transactional(readOnly = true)
    public List<PromoResponseDTO> getAllActivePromos(Integer pageNumber) {
        Page<Promo> promos = promoRepository.findByActive(true,
            PageRequest.of(pageNumber, PAGE_SIZE));
        List<PromoResponseDTO> response = new LinkedList<>();
        for (Promo promo : promos) {
            response.add(PromoResponseDTO.builder()
                .head(promo.getHead())
                .body(promo.getBody())
                .imageData(ImageUtil.decompressImage(promo.getImageData()))
                .build());
        }
        return response;
    }

    @Transactional
    public void activatePromo(String head) {
        Promo promo = promoRepository.findByHead(head).orElseThrow(()
            -> new NoSuchElementException("Promo not found: " + head));
        promo.setActive(true);
        promoRepository.save(promo);
    }

    @Transactional
    public void deactivatePromo(String head) {
        Promo promo = promoRepository.findByHead(head).orElseThrow(()
            -> new NoSuchElementException("Promo not found:" + head));
        promo.setActive(false);
        promoRepository.save(promo);
    }
}
