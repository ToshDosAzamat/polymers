package com.example.polimerlarbackend.controller;


import com.example.polimerlarbackend.dto.CouncilRequest;
import com.example.polimerlarbackend.dto.LabRequest;
import com.example.polimerlarbackend.dto.StatementRequest;
import com.example.polimerlarbackend.model.CouncilTranslation;
import com.example.polimerlarbackend.model.LabTranslation;
import com.example.polimerlarbackend.model.Statement;
import com.example.polimerlarbackend.model.StatementTranslation;
import com.example.polimerlarbackend.service.CouncilService;
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
public class CouncilController {
    private CouncilService councilService;

    public CouncilController(CouncilService councilService) {
        this.councilService = councilService;
    }
    @PostMapping(value = "/council/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<CouncilTranslation>> createLabs(
                                                           @RequestParam("nameUz") String nameUz,
                                                           @RequestParam("nameEn") String nameEn,
                                                           @RequestParam("nameRu") String nameRu,
                                                           @RequestParam("descriptionUz") String descriptionUz,
                                                           @RequestParam("descriptionEn") String descriptionEn,
                                                           @RequestParam("descriptionRu") String descriptionRu,
                                                           @RequestParam MultipartFile file) throws IOException {
        CouncilRequest councilRequest = CouncilRequest.builder()
                .nameUz(nameUz)
                .nameEn(nameEn)
                .nameRu(nameRu)
                .descriptionUz(descriptionUz)
                .descriptionEn(descriptionEn)
                .descriptionRu(descriptionRu)
                .build();
        return new ResponseEntity<>(councilService.createCouncil(councilRequest, file), HttpStatus.CREATED);
    }
    @GetMapping(value = "/council/get/all")
    public ResponseEntity<List<CouncilTranslation>> getAllCouncil(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang){
        return new ResponseEntity<>(councilService.readAllCouncil(lang),HttpStatus.OK);
    }
    @GetMapping(value = "/council/get/{councilId}")
    public ResponseEntity<CouncilTranslation> getByIdCouncil(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang, @PathVariable long councilId){
        return new ResponseEntity<>(councilService.readByIdCouncil(councilId,lang),HttpStatus.OK);
    }
    @PostMapping(value = "/council/update/{councilId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<CouncilTranslation>> updateCouncil(
                                                        @PathVariable long councilId,
                                                        @RequestParam("nameUz") String nameUz,
                                                        @RequestParam("nameEn") String nameEn,
                                                        @RequestParam("nameRu") String nameRu,
                                                        @RequestParam("descriptionUz") String descriptionUz,
                                                        @RequestParam("descriptionEn") String descriptionEn,
                                                        @RequestParam("descriptionRu") String descriptionRu,
                                                        @RequestParam MultipartFile file) throws IOException {
        CouncilRequest councilRequest = CouncilRequest.builder()
                .nameUz(nameUz)
                .nameEn(nameEn)
                .nameRu(nameRu)
                .descriptionUz(descriptionUz)
                .descriptionEn(descriptionEn)
                .descriptionRu(descriptionRu)
                .build();
        return new ResponseEntity<>(councilService.updateByIdCouncil(councilId, councilRequest, file), HttpStatus.CREATED);
    }
    @GetMapping("/council/delete/{councilId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<String> deleteCouncil(@PathVariable long councilId){
        return new ResponseEntity<>(councilService.deleteByIdCouncil(councilId),HttpStatus.OK);
    }
    @PostMapping(value = "/statement/create/{councilId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<StatementTranslation>> createStatement(
                                                        @PathVariable long councilId,
                                                        @RequestParam("titleUz") String titleUz,
                                                        @RequestParam("titleEn") String titleEn,
                                                        @RequestParam("titleRu") String titleRu,
                                                        @RequestParam("textUz") String textUz,
                                                        @RequestParam("textEn") String textEn,
                                                        @RequestParam("textRu") String textRu,
                                                        @RequestParam List<MultipartFile> files) throws IOException {
        StatementRequest statementRequest = StatementRequest.builder()
                .titleUz(titleUz)
                .titleEn(titleEn)
                .titleRu(titleRu)
                .textUz(textUz)
                .textEn(textEn)
                .textRu(textRu)
                .build();
        return new ResponseEntity<>(councilService.createStatement(councilId, statementRequest, files), HttpStatus.CREATED);
    }
    @GetMapping("/statement/get/all/{councilId}")
    public ResponseEntity<List<StatementTranslation>> getAllStatement(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang,@PathVariable long councilId){
        return new ResponseEntity<>(councilService.readAllStatement(councilId,lang), HttpStatus.OK);
    }
    @GetMapping("/statement/get/{statementId}")
    public ResponseEntity<StatementTranslation> getByCouncilIdStatement(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang,@PathVariable long statementId){
        return new ResponseEntity<>(councilService.readByIdStatement(statementId,lang), HttpStatus.OK);
    }
    @GetMapping("/statement/delete/{statementId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<String> getByCouncilIdStatement(@PathVariable long statementId){
        return new ResponseEntity<>(councilService.deleteByIdStatement(statementId), HttpStatus.OK);
    }



}
