package com.example.polimerlarbackend.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class DeviceRequest {
    private String nameUz;
    private String nameEn;
    private String nameRu;

    private String descriptionUz;
    private String descriptionEn;
    private String descriptionRu;
}
