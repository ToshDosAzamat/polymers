package com.example.polimerlarbackend.service;

import com.example.polimerlarbackend.dto.NewsRequest;
import com.example.polimerlarbackend.model.NewsTranslation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public interface NewsService {
    List<NewsTranslation> createNews(NewsRequest newsRequest, List<MultipartFile> files) throws IOException;
    List<NewsTranslation> readAllNews(String lang);
    NewsTranslation readByIdNews(long newsId, String lang);
    String deleteNewsById(long newsId);



}
