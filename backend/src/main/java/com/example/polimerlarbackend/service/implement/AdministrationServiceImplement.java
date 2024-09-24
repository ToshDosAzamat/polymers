package com.example.polimerlarbackend.service.implement;


import com.example.polimerlarbackend.dto.AdministrationRequest;
import com.example.polimerlarbackend.model.Administration;
import com.example.polimerlarbackend.model.AdministrationTranslation;
import com.example.polimerlarbackend.model.Image;
import com.example.polimerlarbackend.repository.AdministrationRepository;
import com.example.polimerlarbackend.repository.AdministrationTranslationRepository;
import com.example.polimerlarbackend.repository.ImageRepository;
import com.example.polimerlarbackend.service.AdministrantionService;
import com.example.polimerlarbackend.service.ConvertImageToWebP;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdministrationServiceImplement implements AdministrantionService {
    private AdministrationRepository administrationRepository;
    private AdministrationTranslationRepository administrationTranslationRepository;
    private ImageRepository imageRepository;
    private ConvertImageToWebP convertImageToWebP;


    public AdministrationServiceImplement(AdministrationRepository administrationRepository,
                                          AdministrationTranslationRepository administrationTranslationRepository,
                                          ImageRepository imageRepository,
                                          ConvertImageToWebP convertImageToWebP) {
        this.administrationRepository = administrationRepository;
        this.administrationTranslationRepository = administrationTranslationRepository;
        this.imageRepository = imageRepository;
        this.convertImageToWebP = convertImageToWebP;
    }

    @Override
    public List<AdministrationTranslation> createAdministration(AdministrationRequest administrationRequest, MultipartFile file) throws IOException {
        Administration administration = new Administration();
        Image image = Image.builder()
                .img(convertImageToWebP.compressImage(file, 200, 300, 0.8f))
                .build();
        AdministrationTranslation admUz = AdministrationTranslation.builder()
                .administration(administration)
                .language("uz")
                .fullname(administrationRequest.getFullnameUz())
                .position(administrationRequest.getPositionUz())
                .image(image)
                .build();
        AdministrationTranslation admEn = AdministrationTranslation.builder()
                .administration(administration)
                .language("en")
                .fullname(administrationRequest.getFullnameEn())
                .position(administrationRequest.getPositionEn())
                .image(image)
                .build();
        AdministrationTranslation admRu = AdministrationTranslation.builder()
                .administration(administration)
                .language("ru")
                .fullname(administrationRequest.getFullnameRu())
                .position(administrationRequest.getPositionRu())
                .image(image)
                .build();
        List<AdministrationTranslation> administrationTranslationList = new ArrayList<>();
        administrationTranslationList.add(admUz);
        administrationTranslationList.add(admEn);
        administrationTranslationList.add(admRu);

        administrationRepository.save(administration);
        imageRepository.save(image);
        administrationTranslationRepository.save(admUz);
        administrationTranslationRepository.save(admEn);
        administrationTranslationRepository.save(admRu);
        return administrationTranslationList;
    }

    @Override
    public List<AdministrationTranslation> readAllAdministration(String lang) {
        List<AdministrationTranslation> administrationTranslationList = administrationTranslationRepository.findByLanguage(lang).orElseThrow(
                ()-> new RuntimeException("Administrations not found! Please enter!"));
        return administrationTranslationList;
    }

    @Override
    public List<AdministrationTranslation> readByIdAdminitration(long adminiId) {
        List<AdministrationTranslation> administrationTranslationList = administrationTranslationRepository.findByAdministration_Id(adminiId).orElseThrow(
                ()-> new RuntimeException(adminiId+" of Administration not found!")
        );
        return administrationTranslationList;
    }

    @Override
    public String deleteByIdAdminitration(long adminiId) {
        boolean adminiDelete = administrationRepository.deleteById(adminiId);
        if(adminiDelete){
            return adminiId+ " of Administration deleting successfull!";
        }else {
            return adminiId+ " of Administration deleting failed!";
        }
    }
}