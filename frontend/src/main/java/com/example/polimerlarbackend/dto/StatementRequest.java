package com.example.polimerlarbackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatementRequest {
    private String titleUz;
    private String titleEn;
    private String titleRu;

    private String textUz;
    private String textEn;
    private String textRu;
}
