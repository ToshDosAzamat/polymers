package com.example.polimerlarbackend.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdministrationRequest {
    private String fullnameUz;
    private String fullnameEn;
    private String fullnameRu;

    private String positionUz;
    private String positionEn;
    private String positionRu;
}
