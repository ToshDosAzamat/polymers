package com.example.polimerlarbackend.service.implement;


import com.example.polimerlarbackend.dto.CouncilRequest;
import com.example.polimerlarbackend.dto.StatementRequest;
import com.example.polimerlarbackend.model.*;
import com.example.polimerlarbackend.repository.*;
import com.example.polimerlarbackend.service.ConvertImageToWebP;
import com.example.polimerlarbackend.service.CouncilService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class CouncilServiceImplement implements CouncilService {
    private final CouncilRepository councilRepository;
    private final CouncilTranslationRepository councilTranslationRepository;
    private final ImageRepository imageRepository;
    private final StatementRepository statementRepository;
    private final StatementTranslationRepository statementTranslationRepository;
    private ConvertImageToWebP convertImageToWebP;

    public CouncilServiceImplement(CouncilRepository councilRepository,
                                   CouncilTranslationRepository councilTranslationRepository,
                                   ImageRepository imageRepository,
                                   StatementRepository statementRepository,
                                   StatementTranslationRepository statementTranslationRepository,
                                   ConvertImageToWebP convertImageToWebP) {
        this.councilRepository = councilRepository;
        this.councilTranslationRepository = councilTranslationRepository;
        this.imageRepository = imageRepository;
        this.statementRepository = statementRepository;
        this.statementTranslationRepository = statementTranslationRepository;
        this.convertImageToWebP = convertImageToWebP;
    }

    @Override
    public List<CouncilTranslation> createCouncil(CouncilRequest councilRequest, MultipartFile file) throws IOException {
        Council council = new Council();
        Statement statement = new Statement();
        Image image = new Image();
        image.setImg(convertImageToWebP.compressImage(file, 800, 600, 0.8f));
        CouncilTranslation councilUz = CouncilTranslation.builder()
                .council(council)
                .statement(statement)
                .language("uz")
                .name(councilRequest.getNameUz())
                .description(councilRequest.getDescriptionUz())
                .image(image)
                .build();
        CouncilTranslation councilEn = CouncilTranslation.builder()
                .council(council)
                .statement(statement)
                .language("en")
                .name(councilRequest.getNameEn())
                .description(councilRequest.getDescriptionEn())
                .image(image)
                .build();
        CouncilTranslation councilRu = CouncilTranslation.builder()
                .council(council)
                .statement(statement)
                .language("ru")
                .name(councilRequest.getNameRu())
                .description(councilRequest.getDescriptionRu())
                .image(image)
                .build();
        councilRepository.save(council);
        imageRepository.save(image);
        statementRepository.save(statement);
        councilTranslationRepository.save(councilUz);
        councilTranslationRepository.save(councilEn);
        councilTranslationRepository.save(councilRu);
        List<CouncilTranslation> list = new ArrayList<>();
        list.add(councilUz);
        list.add(councilEn);
        list.add(councilRu);
        return list;
    }

    @Override
    public List<CouncilTranslation> readAllCouncil(String lang) {
        List<CouncilTranslation> list = councilTranslationRepository.findByLanguage(lang).orElseThrow(
                ()->new RuntimeException("Councils not found!")
        );
        return list;
    }
    @Override
    public CouncilTranslation readByIdCouncil(long councilId, String lang) {
        CouncilTranslation council = councilTranslationRepository.findByCouncil_IdAndLanguage(councilId,lang).orElseThrow(
                ()->new RuntimeException(councilId+" of Council not found!")
        );
        return council;
    }

    @Override
    public List<CouncilTranslation> updateByIdCouncil(long councilId, CouncilRequest councilRequest, MultipartFile file) throws IOException {
        Council council = councilRepository.findById(councilId).orElseThrow(
                ()-> new RuntimeException(councilId+" of council not found!")
        );
        CouncilTranslation counUz = councilTranslationRepository.findByCouncil_IdAndLanguage(councilId, "uz").orElseThrow(
                ()->new RuntimeException(councilId+" [UZ]Council not found")
        );
        CouncilTranslation counEn = councilTranslationRepository.findByCouncil_IdAndLanguage(councilId, "en").orElseThrow(
                ()->new RuntimeException(councilId+" [EN]Council not found")
        );
        CouncilTranslation counRu = councilTranslationRepository.findByCouncil_IdAndLanguage(councilId, "ru").orElseThrow(
                ()->new RuntimeException(councilId+" [RU] Council not found")
        );
        Image image = imageRepository.findById(counUz.getImage().getId()).orElseThrow(
                ()-> new RuntimeException("Image not found!")
        );
        image.setImg(convertImageToWebP.compressImage(file, 800, 600, 0.8f));
        counUz.setCouncil(council);
        counUz.setStatement(counUz.getStatement());
        counUz.setLanguage("uz");
        counUz.setName(councilRequest.getNameUz());
        counUz.setDescription(councilRequest.getDescriptionUz());
        counUz.setImage(image);

        counEn.setCouncil(council);
        counEn.setStatement(counEn.getStatement());
        counEn.setLanguage("en");
        counEn.setName(councilRequest.getNameEn());
        counEn.setDescription(councilRequest.getDescriptionEn());
        counEn.setImage(image);

        counRu.setCouncil(council);
        counRu.setStatement(counRu.getStatement());
        counRu.setLanguage("ru");
        counRu.setName(councilRequest.getNameRu());
        counRu.setDescription(councilRequest.getDescriptionRu());
        counRu.setImage(image);

        councilRepository.save(council);
        imageRepository.save(image);
        councilTranslationRepository.save(counUz);
        councilTranslationRepository.save(counEn);
        councilTranslationRepository.save(counRu);
        List<CouncilTranslation> list = new ArrayList<>();
        list.add(counUz);
        list.add(counEn);
        list.add(counRu);
        return list;
    }

    @Override
    public String deleteByIdCouncil(long councilId) {
        if(councilRepository.deleteById(councilId)){
            return councilId+" of council deleting successful!";
        } else{
            return  councilId+" of council deleting failed!";
        }
    }

    @Override
    public List<StatementTranslation> createStatement(long councilId, StatementRequest statementRequest, List<MultipartFile> files) throws IOException {
        Council council = councilRepository.findById(councilId).orElseThrow(
                ()-> new RuntimeException(councilId+" of Council not found!")
        );
        Statement statement = new Statement();
        Set<Image> images = new HashSet<>();
        for(MultipartFile file: files){
            Image image = new Image();
            image.setImg(convertImageToWebP.compressImage(file, 800, 600, 0.8f));
            imageRepository.save(image);
            images.add(image);
        }

        StatementTranslation statUz = StatementTranslation.builder()
                .council(council)
                .statement(statement)
                .language("uz")
                .date(new Date())
                .name(statementRequest.getTitleUz())
                .description(statementRequest.getTextUz())
                .images(images)
                .build();

        StatementTranslation statEn = StatementTranslation.builder()
                .council(council)
                .statement(statement)
                .language("en")
                .date(new Date())
                .name(statementRequest.getTitleEn())
                .description(statementRequest.getTextEn())
                .images(images)
                .build();
        StatementTranslation statRu = StatementTranslation.builder()
                .council(council)
                .statement(statement)
                .language("ru")
                .date(new Date())
                .name(statementRequest.getTitleRu())
                .description(statementRequest.getTextRu())
                .images(images)
                .build();
        statementRepository.save(statement);
        statementTranslationRepository.save(statUz);
        statementTranslationRepository.save(statEn);
        statementTranslationRepository.save(statRu);
        List<StatementTranslation> list = new ArrayList<>();
        list.add(statUz);
        list.add(statEn);
        list.add(statRu);
        return list;
    }

    @Override
    public List<StatementTranslation> readAllStatement(long councilId, String lang) {
        List<StatementTranslation> list = statementTranslationRepository.findByCouncil_IdAndLanguage(councilId,lang).orElseThrow(
                ()-> new RuntimeException("Statements not found!")
        );
        return list;
    }

    @Override
    public StatementTranslation readByIdStatement(long statId, String lang) {
        StatementTranslation stat = statementTranslationRepository.findByStatement_IdAndLanguage(statId, lang).orElseThrow(
                ()-> new RuntimeException(statId+"Statement not found!")
        );
        return null;
    }
    @Override
    public String deleteByIdStatement(long statId) {
        if(statementRepository.deleteById(statId)){
            return statId+" Statement deleting successful!";
        }else{
            return statId+" Statement deleting failed!";
        }
    }
}