package com.oceanapplication.ocean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromoCreateRequestDTO {

    private String head;
    private String body;
    private MultipartFile image;

}
