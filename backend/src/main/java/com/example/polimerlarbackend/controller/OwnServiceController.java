package com.example.polimerlarbackend.controller;

import com.example.polimerlarbackend.dto.OwnServiceRequest;
import com.example.polimerlarbackend.model.OwnService;
import com.example.polimerlarbackend.model.OwnServiceTranslation;
import com.example.polimerlarbackend.service.OwnServiceService;
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
public class OwnServiceController {
    private OwnServiceService ownServiceService;

    public OwnServiceController(OwnServiceService ownServiceService) {
        this.ownServiceService = ownServiceService;
    }
    @PostMapping(value = "/ownservice/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<OwnServiceTranslation>> cretaeOwnService(@RequestParam("nameUz") String nameUz,
                                                                        @RequestParam("nameEn") String nameEn,
                                                                        @RequestParam("nameRu") String nameRu,
                                                                        @RequestParam("descriptionUz") String descriptionUz,
                                                                        @RequestParam("descriptionEn") String descriptionEn,
                                                                        @RequestParam("descriptionRu") String descriptionRu,
                                                                        @RequestParam MultipartFile file) throws IOException{
        OwnServiceRequest req = OwnServiceRequest.builder()
                .nameUz(nameUz)
                .nameEn(nameEn)
                .nameRu(nameRu)
                .descriptionUz(descriptionUz)
                .descriptionEn(descriptionEn)
                .descriptionRu(descriptionRu)
                .build();
        return new ResponseEntity<>(ownServiceService.createOwnService(req, file), HttpStatus.CREATED);
    }
    @GetMapping("/ownservice/get/all")
    public ResponseEntity<List<OwnServiceTranslation>> readingByLang(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang){
        return new ResponseEntity<>(ownServiceService.readAllOwnService(lang), HttpStatus.OK);
    }
    @GetMapping("/ownservice/get/{id}")
    public ResponseEntity<List<OwnServiceTranslation>> readingById(@PathVariable long id){
        return new ResponseEntity<>(ownServiceService.readByIdOwnService(id), HttpStatus.OK);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deletingById(@PathVariable long id){
        return new ResponseEntity<>(ownServiceService.deleteOwnService(id), HttpStatus.OK);
    }
}
