package com.example.polimerlarbackend.service;

import com.example.polimerlarbackend.dto.OwnServiceRequest;
import com.example.polimerlarbackend.model.OwnServiceTranslation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public interface OwnServiceService {
    List<OwnServiceTranslation> createOwnService(OwnServiceRequest ownServiceRequest, MultipartFile file) throws IOException;
    List<OwnServiceTranslation> readAllOwnService(String lang);
    List<OwnServiceTranslation> readByIdOwnService(long id);
    String deleteOwnService(long id);
}
