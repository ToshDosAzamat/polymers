package com.example.polimerlarbackend.controller;


import com.example.polimerlarbackend.dto.DeviceRequest;
import com.example.polimerlarbackend.dto.LabRequest;
import com.example.polimerlarbackend.model.DeviceTranslation;
import com.example.polimerlarbackend.model.LabTranslation;
import com.example.polimerlarbackend.service.LabService;
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
public class LabsController {
    private LabService labService;

    public LabsController(LabService labService) {
        this.labService = labService;
    }

    @PostMapping(value = "/lab/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<LabTranslation>> createLabs(@RequestParam("nameUz") String nameUz,
                                                           @RequestParam("nameEn") String nameEn,
                                                           @RequestParam("nameRu") String nameRu,
                                                           @RequestParam("descriptionUz") String descriptionUz,
                                                           @RequestParam("descriptionEn") String descriptionEn,
                                                           @RequestParam("descriptionRu") String descriptionRu,
                                                           @RequestParam MultipartFile file) throws IOException {
        LabRequest labRequest = LabRequest.builder()
                .nameUz(nameUz)
                .nameEn(nameEn)
                .nameRu(nameRu)
                .descriptionUz(descriptionUz)
                .descriptionEn(descriptionEn)
                .descriptionRu(descriptionRu)
                .build();
        return new ResponseEntity<>(labService.createLab(labRequest, file), HttpStatus.CREATED);
    }
    @GetMapping("/lab/get/all")
    public ResponseEntity<Set<LabTranslation>> readAllLabs(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang){
        return new ResponseEntity<>(labService.readAllLab(lang), HttpStatus.OK);
    }
    @GetMapping("/lab/get/{labid}")
    public ResponseEntity<LabTranslation> readOneLab(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang, @PathVariable long labid){
        return new ResponseEntity<>(labService.readByIdLab(labid,lang), HttpStatus.OK);
    }
    @PostMapping(value = "/lab/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<LabTranslation>> updateLabs(@RequestParam("labid") long labid,
                                                           @RequestParam("nameUz") String nameUz,
                                                           @RequestParam("nameEn") String nameEn,
                                                           @RequestParam("nameRu") String nameRu,
                                                           @RequestParam("descriptionUz") String descriptionUz,
                                                           @RequestParam("descriptionEn") String descriptionEn,
                                                           @RequestParam("descriptionRu") String descriptionRu,
                                                           @RequestParam MultipartFile file) throws IOException {
        LabRequest labRequest = LabRequest.builder()
                .nameUz(nameUz)
                .nameEn(nameEn)
                .nameRu(nameRu)
                .descriptionUz(descriptionUz)
                .descriptionEn(descriptionEn)
                .descriptionRu(descriptionRu)
                .build();
        return new ResponseEntity<>(labService.updateLab(labid, labRequest, file), HttpStatus.CREATED);
    }
    @GetMapping("/lab/delete/{labid}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<Void> deleteLab(@PathVariable long labid){
        labService.deleteLab(labid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/device/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<DeviceTranslation>> createDevice(@RequestParam("labid") long labid,
                                                                @RequestParam("nameUz") String nameUz,
                                                                @RequestParam("nameEn") String nameEn,
                                                                @RequestParam("nameRu") String nameRu,
                                                                @RequestParam("descriptionUz") String descriptionUz,
                                                                @RequestParam("descriptionEn") String descriptionEn,
                                                                @RequestParam("descriptionRu") String descriptionRu,
                                                                @RequestParam  MultipartFile file) throws IOException {
        DeviceRequest deviceRequest = DeviceRequest.builder()
                .nameUz(nameUz)
                .nameEn(nameEn)
                .nameRu(nameRu)
                .descriptionUz(descriptionUz)
                .descriptionEn(descriptionEn)
                .descriptionRu(descriptionRu)
                .build();
        return new ResponseEntity<>(labService.createDevice(labid, deviceRequest, file),HttpStatus.CREATED);
    }
    @GetMapping("/device/get/all/{labid}")
    public ResponseEntity<List<DeviceTranslation>> readAllDevices(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang, @PathVariable long labid){
        return new ResponseEntity<>(labService.readAllDevice(labid, lang),HttpStatus.OK);
    }
    @GetMapping("/device/get/{deviceid}")
    public ResponseEntity<DeviceTranslation> readOneDevice(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang, @PathVariable long deviceid){
        return new ResponseEntity<>(labService.readByIdDevice(deviceid, lang),HttpStatus.OK);
    }
    @GetMapping("/device/delete/{deviceid}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<Void> deleteDevice(@PathVariable long deviceid){
        labService.deleteDevice(deviceid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
