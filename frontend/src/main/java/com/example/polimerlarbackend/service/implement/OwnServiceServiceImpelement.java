package com.example.polimerlarbackend.service.implement;

import com.example.polimerlarbackend.dto.OwnServiceRequest;
import com.example.polimerlarbackend.model.*;
import com.example.polimerlarbackend.repository.ImageRepository;
import com.example.polimerlarbackend.repository.OwnServiceRepository;
import com.example.polimerlarbackend.repository.OwnServiceTranslationRepository;
import com.example.polimerlarbackend.service.ConvertImageToWebP;
import com.example.polimerlarbackend.service.OwnServiceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OwnServiceServiceImpelement implements OwnServiceService {
    private OwnServiceRepository ownServiceRepository;
    private OwnServiceTranslationRepository ownServiceTranslationRepository;
    private ImageRepository imageRepository;

    private ConvertImageToWebP convertImageToWebP;

    public OwnServiceServiceImpelement(OwnServiceRepository ownServiceRepository,
                                       OwnServiceTranslationRepository ownServiceTranslationRepository,
                                       ImageRepository imageRepository,
                                       ConvertImageToWebP convertImageToWebP) {
        this.ownServiceRepository = ownServiceRepository;
        this.ownServiceTranslationRepository = ownServiceTranslationRepository;
        this.imageRepository = imageRepository;
        this.convertImageToWebP = convertImageToWebP;
    }

    @Override
    public List<OwnServiceTranslation> createOwnService(OwnServiceRequest ownServiceRequest, MultipartFile file) throws IOException {
        OwnService ownService = new OwnService();
        List<OwnServiceTranslation> OwnServiceTranslationList = new ArrayList<>();
        Image image = Image.builder()
                .img(convertImageToWebP.compressImage(file, 800, 600, 0.8f))
                .build();
        OwnServiceTranslation ownsUz = OwnServiceTranslation.builder()
                .ownService(ownService)
                .language("uz")
                .name(ownServiceRequest.getNameUz())
                .description(ownServiceRequest.getDescriptionUz())
                .image(image)
                .build();
        OwnServiceTranslation ownsEn = OwnServiceTranslation.builder()
                .ownService(ownService)
                .language("en")
                .name(ownServiceRequest.getNameEn())
                .description(ownServiceRequest.getDescriptionEn())
                .image(image)
                .build();
        OwnServiceTranslation ownsRu = OwnServiceTranslation.builder()
                .ownService(ownService)
                .language("ru")
                .name(ownServiceRequest.getNameRu())
                .description(ownServiceRequest.getDescriptionRu())
                .image(image)
                .build();
        OwnServiceTranslationList.add(ownsUz);
        OwnServiceTranslationList.add(ownsEn);
        OwnServiceTranslationList.add(ownsRu);
        ownServiceRepository.save(ownService);
        imageRepository.save(image);
        ownServiceTranslationRepository.save(ownsUz);
        ownServiceTranslationRepository.save(ownsEn);
        ownServiceTranslationRepository.save(ownsRu);
        return OwnServiceTranslationList;
    }

    @Override
    public List<OwnServiceTranslation> readAllOwnService(String lang) {
        return ownServiceTranslationRepository.findByLanguage(lang).orElseThrow(
                ()-> new RuntimeException("Language:"+lang+" Service not found!")
        );
    }

    @Override
    public List<OwnServiceTranslation> readByIdOwnService(long id) {
        return ownServiceTranslationRepository.findByOwnService_Id(id).orElseThrow(
                ()->new RuntimeException("ID:"+id+" Service not found!")
        );
    }

    @Override
    public String deleteOwnService(long id) {
        if(ownServiceRepository.deleteById(id)){
            return "ID:"+id+" service deleting is succcessful!";
        } else{
            return "ID:"+id+" service deleting is failed!";
        }
    }
}