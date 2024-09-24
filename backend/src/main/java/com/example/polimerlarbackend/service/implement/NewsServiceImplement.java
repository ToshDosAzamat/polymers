package com.example.polimerlarbackend.service.implement;

import com.example.polimerlarbackend.dto.NewsRequest;
import com.example.polimerlarbackend.model.Image;
import com.example.polimerlarbackend.model.News;
import com.example.polimerlarbackend.model.NewsTranslation;
import com.example.polimerlarbackend.repository.ImageRepository;

import com.example.polimerlarbackend.repository.NewsRepository;
import com.example.polimerlarbackend.repository.NewsTranslationRepository;
import com.example.polimerlarbackend.service.ConvertImageToWebP;
import com.example.polimerlarbackend.service.NewsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class NewsServiceImplement implements NewsService {
    private NewsRepository newsRepository;
    private NewsTranslationRepository newsTranslationRepository;
    private ImageRepository imageRepository;
    private ConvertImageToWebP convertImageToWebP;

    public NewsServiceImplement(NewsRepository newsRepository,
                                NewsTranslationRepository newsTranslationRepository,
                                ImageRepository imageRepository,
                                ConvertImageToWebP convertImageToWebP) {
        this.newsRepository = newsRepository;
        this.newsTranslationRepository = newsTranslationRepository;
        this.imageRepository = imageRepository;
        this.convertImageToWebP = convertImageToWebP;
    }

    @Override
    public List<NewsTranslation> createNews(NewsRequest newsRequest, List<MultipartFile> files) throws IOException {
        News news = new News();
        Set<Image> images = new HashSet<>();
        for(MultipartFile file: files){
            Image image = new Image();
            image.setImg(convertImageToWebP.compressImage(file, 800, 600, 0.8f));
            imageRepository.save(image);
            images.add(image);
        }
        NewsTranslation newsUz = NewsTranslation.builder()
                .news(news)
                .language("uz")
                .title(newsRequest.getTitleUz())
                .text(newsRequest.getTextUz())
                .date(new Date())
                .images(images)
                .build();

        NewsTranslation newsEn = NewsTranslation.builder()
                .news(news)
                .language("en")
                .title(newsRequest.getTitleEn())
                .text(newsRequest.getTextEn())
                .date(new Date())
                .images(images)
                .build();

        NewsTranslation newsRu = NewsTranslation.builder()
                .news(news)
                .language("ru")
                .title(newsRequest.getTitleRu())
                .text(newsRequest.getTextRu())
                .date(new Date())
                .images(images)
                .build();
        newsRepository.save(news);
        newsTranslationRepository.save(newsUz);
        newsTranslationRepository.save(newsEn);
        newsTranslationRepository.save(newsRu);
        List<NewsTranslation> lists = new ArrayList<>();
        lists.add(newsUz);
        lists.add(newsEn);
        lists.add(newsRu);
        return lists;
    }

    @Override
    public List<NewsTranslation> readAllNews(String lang) {
        return newsTranslationRepository.findByLanguage(lang).orElseThrow(
                ()-> new RuntimeException("News not found!")
        );
    }

    @Override
    public NewsTranslation readByIdNews(long newsId, String lang) {
        return newsTranslationRepository.findByNews_idAndLanguage(newsId, lang).orElseThrow(
                ()-> new RuntimeException(newsId+" of News not found!")
        );
    }
    @Override
    public String deleteNewsById(long newsId) {
        if(newsRepository.deleteById(newsId)){
            return "Deleting is successful!";
        } else {
            return "Deleting is failed";
        }
    }
}