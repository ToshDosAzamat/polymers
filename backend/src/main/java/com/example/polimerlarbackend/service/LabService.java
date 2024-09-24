package com.example.polimerlarbackend.service;


import com.example.polimerlarbackend.dto.DeviceRequest;
import com.example.polimerlarbackend.dto.LabRequest;
import com.example.polimerlarbackend.model.DeviceTranslation;
import com.example.polimerlarbackend.model.LabTranslation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public interface LabService {

    //--------Lab service
    List<LabTranslation> createLab(LabRequest labRequest, MultipartFile file) throws IOException;
    Set<LabTranslation> readAllLab(String lang);
    LabTranslation readByIdLab(long labId ,String lang);
    List<LabTranslation> updateLab(long labId, LabRequest labRequest,MultipartFile file) throws IOException;
    void deleteLab(long labId);


    //------Device service because bind Lab
    List<DeviceTranslation> createDevice(long labId, DeviceRequest deviceRequest, MultipartFile file) throws IOException;
    List<DeviceTranslation> readAllDevice(long labId, String lang);
    DeviceTranslation readByIdDevice(long deviceId, String lang);
    void deleteDevice(long deviceId);

}
