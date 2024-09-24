package com.example.polimerlarbackend.controller;


import com.example.polimerlarbackend.dto.NewsRequest;
import com.example.polimerlarbackend.model.NewsTranslation;
import com.example.polimerlarbackend.service.NewsService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
public class NewsController {
    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    @PostMapping(value = "/news/create",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<NewsTranslation>> creatingNews(
                                    @RequestParam("titleUz") String titleUz,
                                    @RequestParam("titleEn") String titleEn,
                                    @RequestParam("titleRu") String titleRu,
                                    @RequestParam("textUz") String textUz,
                                    @RequestParam("textEn") String textEn,
                                    @RequestParam("textRu") String textRu,
                                    @RequestParam List<MultipartFile> files) throws IOException {
        NewsRequest newsRequest = NewsRequest.builder()
                .titleUz(titleUz)
                .titleEn(titleEn)
                .titleRu(titleRu)
                .textUz(textUz)
                .textEn(textEn)
                .textRu(textRu)
                .build();
        return new ResponseEntity<>(newsService.createNews(newsRequest, files), HttpStatus.CREATED);
    }
    @GetMapping(value = "/news/get/all")
    public ResponseEntity<List<NewsTranslation>> getingAll(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang){
        return new ResponseEntity<>(newsService.readAllNews(lang),HttpStatus.OK);
    }
    @GetMapping(value = "/news/get/{newsId}")
    public ResponseEntity<NewsTranslation> getingById(@RequestParam(name = "lang", required = false, defaultValue = "uz") String lang, @PathVariable long newId){
        return new ResponseEntity<>(newsService.readByIdNews(newId,lang), HttpStatus.OK);
    }
    @GetMapping(value = "/news/delete/{newsId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<String> deletingNews(@PathVariable long newsId){
        return new ResponseEntity<>(newsService.deleteNewsById(newsId),HttpStatus.OK);
    }
}
