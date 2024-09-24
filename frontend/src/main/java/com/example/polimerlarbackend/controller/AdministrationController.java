package com.example.polimerlarbackend.controller;


import com.example.polimerlarbackend.dto.AdministrationRequest;
import com.example.polimerlarbackend.model.AdministrationTranslation;
import com.example.polimerlarbackend.service.AdministrantionService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class AdministrationController {
    private AdministrantionService administrantionService;

    public AdministrationController(AdministrantionService administrantionService) {
        this.administrantionService = administrantionService;
    }

    @PostMapping(value = "/administration/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<AdministrationTranslation>> createAdministration(@RequestParam("fullnameUz") String fullnameUz,
                                                                                @RequestParam("fullnameEn") String fullnameEn,
                                                                                @RequestParam("fullnameRu") String fullnameRu,
                                                                                @RequestParam("positionUz") String positionUz,
                                                                                @RequestParam("positionEn") String positionEn,
                                                                                @RequestParam("positionRu") String positionRu,
                                                                                @RequestParam MultipartFile file) throws IOException {
        AdministrationRequest request = AdministrationRequest.builder()
                .fullnameUz(fullnameUz)
                .fullnameRu(fullnameRu)
                .fullnameEn(fullnameEn)
                .positionUz(positionUz)
                .positionEn(positionEn)
                .positionRu(positionRu)
                .build();
        return new ResponseEntity<>(administrantionService.createAdministration(request,file), HttpStatus.CREATED);
    }
    @GetMapping("/administration/get/all")
    public ResponseEntity<List<AdministrationTranslation>> readAllAdministration(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang){
        return new ResponseEntity<>(administrantionService.readAllAdministration(lang),HttpStatus.OK);
    }
    @GetMapping("/administration/get/{administrationId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<AdministrationTranslation>> readAllAdministration(@PathVariable long administrationId){
        return new ResponseEntity<>(administrantionService.readByIdAdminitration(administrationId),HttpStatus.OK);
    }
    @GetMapping("/administration/delete/{administrationId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<String> deleteByIdAdministration(@PathVariable long administrationId){
        return new ResponseEntity<>(administrantionService.deleteByIdAdminitration(administrationId),HttpStatus.OK);
    }
}
