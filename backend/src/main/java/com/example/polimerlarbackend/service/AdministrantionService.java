package com.example.polimerlarbackend.service;

import com.example.polimerlarbackend.dto.AdministrationRequest;
import com.example.polimerlarbackend.model.AdministrationTranslation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public interface AdministrantionService {
    List<AdministrationTranslation> createAdministration(AdministrationRequest administrationRequest, MultipartFile file) throws IOException;
    List<AdministrationTranslation> readAllAdministration(String lang);
    List<AdministrationTranslation> readByIdAdminitration(long adminiId);
    String deleteByIdAdminitration(long adminiId);
}
