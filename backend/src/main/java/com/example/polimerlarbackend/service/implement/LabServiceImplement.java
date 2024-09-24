
package com.example.polimerlarbackend.service.implement;

import com.example.polimerlarbackend.dto.DeviceRequest;
import com.example.polimerlarbackend.dto.LabRequest;
import com.example.polimerlarbackend.model.*;
import com.example.polimerlarbackend.repository.*;
import com.example.polimerlarbackend.service.ConvertImageToWebP;
import com.example.polimerlarbackend.service.LabService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;


@Service
@Transactional
public class LabServiceImplement implements LabService {
    private LabRepository labRepository;
    private LabTranslationRepository labTranslationRepository;
    private DeviceRepository deviceRepository;
    private DeviceTranslationRepository deviceTranslationRepository;
    private ImageRepository imageRepository;
    private ConvertImageToWebP convertImageToWebP;

    public LabServiceImplement(LabRepository labRepository,
                               LabTranslationRepository labTranslationRepository,
                               DeviceRepository deviceRepository,
                               DeviceTranslationRepository deviceTranslationRepository,
                               ImageRepository imageRepository,
                               ConvertImageToWebP convertImageToWebP) {
        this.labRepository = labRepository;
        this.labTranslationRepository = labTranslationRepository;
        this.deviceRepository = deviceRepository;
        this.deviceTranslationRepository = deviceTranslationRepository;
        this.imageRepository = imageRepository;
        this.convertImageToWebP = convertImageToWebP;
    }

    @Override
    public List<LabTranslation> createLab(LabRequest labRequest, MultipartFile file) throws IOException {
        Lab lab = new Lab();
        List<LabTranslation> labTranslationList = new ArrayList<>();
        byte[] img_byte=convertImageToWebP.compressImage(file, 800, 600, 0.8f);
        System.out.println(img_byte);
        Image image = Image.builder()
                .img(img_byte)
                .build();
        LabTranslation labUz = LabTranslation.builder()
                .lab(lab)
                .language("uz")
                .name(labRequest.getNameUz())
                .description(labRequest.getDescriptionUz())
                .image(image)
                .build();
        LabTranslation labEn = LabTranslation.builder()
                .lab(lab)
                .language("en")
                .name(labRequest.getNameEn())
                .description(labRequest.getDescriptionEn())
                .image(image)
                .build();
        LabTranslation labRu = LabTranslation.builder()
                .lab(lab)
                .language("ru")
                .name(labRequest.getNameRu())
                .description(labRequest.getDescriptionRu())
                .image(image)
                .build();
        labTranslationList.add(labUz);
        labTranslationList.add(labEn);
        labTranslationList.add(labRu);
        labRepository.save(lab);
        imageRepository.save(image);
        labTranslationRepository.save(labUz);
        labTranslationRepository.save(labEn);
        labTranslationRepository.save(labRu);
        return labTranslationList;
    }

    @Override
    public Set<LabTranslation> readAllLab(String lang) {
        Set<LabTranslation> labLang = labTranslationRepository.findByLanguage(lang).orElseThrow(
                ()-> new RuntimeException("Labs not found! Please enter labs!")
        );
        return labLang;
    }

    @Override
    public LabTranslation readByIdLab(long labId, String lang) {
        LabTranslation labTranslation = labTranslationRepository.findByLab_idAndLanguage(labId, lang).orElseThrow(
                ()-> new RuntimeException("Lab not found! Please enter lab!")
        );
        return labTranslation;
    }

    @Override
    public List<LabTranslation> updateLab(long labId, LabRequest labRequest, MultipartFile file) throws IOException {
        List<LabTranslation> labTranslationList = new ArrayList<>();
        Lab lab = labRepository.findById(labId).orElseThrow(
                ()-> new RuntimeException(labId +"of Lab not found!")
        );
        LabTranslation labUz = labTranslationRepository.findByLab_idAndLanguage(labId, "uz").orElseThrow(
                ()-> new RuntimeException("Lab not found!")
        );
        LabTranslation labEn = labTranslationRepository.findByLab_idAndLanguage(labId,"en").orElseThrow(
                ()-> new RuntimeException("Lab not found!")
        );
        LabTranslation labRu = labTranslationRepository.findByLab_idAndLanguage(labId, "ru").orElseThrow(
                ()-> new RuntimeException("Lab not found!")
        );
        Image image = imageRepository.findById(labUz.getImage().getId()).orElseThrow(
                () -> new RuntimeException("Image not found!")
        );
        image.setImg(convertImageToWebP.compressImage(file, 800, 600, 0.8f));
        labUz.setLab(lab);
        labUz.setLanguage("uz");
        labUz.setName(labRequest.getNameUz());
        labUz.setDescription(labRequest.getDescriptionUz());
        labUz.setImage(image);

        labEn.setLab(lab);
        labEn.setLanguage("en");
        labEn.setName(labRequest.getNameEn());
        labEn.setDescription(labRequest.getDescriptionEn());
        labEn.setImage(image);

        labRu.setLab(lab);
        labRu.setLanguage("ru");
        labRu.setName(labRequest.getNameRu());
        labRu.setDescription(labRequest.getDescriptionRu());
        labRu.setImage(image);

        labTranslationList.add(labUz);
        labTranslationList.add(labEn);
        labTranslationList.add(labRu);
        imageRepository.save(image);
        labRepository.save(lab);
        labTranslationRepository.save(labUz);
        labTranslationRepository.save(labEn);
        labTranslationRepository.save(labRu);
        return labTranslationList;
    }

    @Override
    public void deleteLab(long labId) {
        labRepository.deleteById(labId);
    }

    @Override
    public List<DeviceTranslation> createDevice(long labId, DeviceRequest deviceRequest, MultipartFile file) throws IOException {
        Lab lab = labRepository.findById(labId).orElseThrow(
                ()-> new RuntimeException("lab not found!")
        );
        Device device = new Device();

        Image image = new Image();
        image.setImg(file.getBytes());

        DeviceTranslation devUz = DeviceTranslation.builder()
                .language("uz")
                .lab(lab)
                .device(device)
                .name(deviceRequest.getNameUz())
                .description(deviceRequest.getDescriptionUz())
                .image(image)
                .build();
        DeviceTranslation devEn = DeviceTranslation.builder()
                .lab(lab)
                .device(device)
                .language("en")
                .name(deviceRequest.getNameEn())
                .description(deviceRequest.getDescriptionEn())
                .image(image)
                .build();
        DeviceTranslation devRu = DeviceTranslation.builder()
                .lab(lab)
                .device(device)
                .language("ru")
                .name(deviceRequest.getNameRu())
                .description(deviceRequest.getDescriptionRu())
                .image(image)
                .build();
        List<DeviceTranslation> deviceTranslationList = new ArrayList<>();
        deviceTranslationList.add(devUz);
        deviceTranslationList.add(devEn);
        deviceTranslationList.add(devRu);
        imageRepository.save(image);
        deviceRepository.save(device);
        deviceTranslationRepository.save(devUz);
        deviceTranslationRepository.save(devEn);
        deviceTranslationRepository.save(devRu);
        return deviceTranslationList;
    }

    @Override
    public List<DeviceTranslation> readAllDevice(long labId, String lang) {
        List<DeviceTranslation> deviceTranslationList = deviceTranslationRepository.findByLab_idAndLanguage(labId, lang).orElseThrow(
                ()-> new RuntimeException("Device not found!")
        );
        return deviceTranslationList;
    }

    @Override
    public DeviceTranslation readByIdDevice(long deviceId, String lang) {
        DeviceTranslation deviceTranslation = deviceTranslationRepository.findByDevice_IdAndLanguage(deviceId,lang).orElseThrow(
                ()-> new RuntimeException(deviceId+"of Device not found!")
        );
        return deviceTranslation;
    }

    @Override
    public void deleteDevice(long deviceId) {
        deviceRepository.deleteById(deviceId);
    }
}
