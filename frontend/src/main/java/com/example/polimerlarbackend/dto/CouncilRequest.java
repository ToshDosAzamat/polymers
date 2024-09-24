package com.example.polimerlarbackend.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouncilRequest {
    private String nameUz;
    private String nameEn;
    private String nameRu;

    private String descriptionUz;
    private String descriptionEn;
    private String descriptionRu;
}
