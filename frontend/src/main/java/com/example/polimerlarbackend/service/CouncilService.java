package com.example.polimerlarbackend.service;

import com.example.polimerlarbackend.dto.CouncilRequest;
import com.example.polimerlarbackend.dto.StatementRequest;
import com.example.polimerlarbackend.model.CouncilTranslation;
import com.example.polimerlarbackend.model.StatementTranslation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public interface CouncilService {

    //Council CRUD
    List<CouncilTranslation> createCouncil(CouncilRequest councilRequest, MultipartFile file) throws IOException;
    List<CouncilTranslation> readAllCouncil(String lang);
    CouncilTranslation readByIdCouncil(long councilId,String lang);
    List<CouncilTranslation> updateByIdCouncil(long councilId, CouncilRequest councilRequest,MultipartFile file) throws IOException;
    String deleteByIdCouncil(long councilId);
    //Statement CRUD
    List<StatementTranslation> createStatement(long councilId, StatementRequest statementRequest, List<MultipartFile> files) throws IOException;
    List<StatementTranslation> readAllStatement(long councilId, String lang);
    StatementTranslation readByIdStatement(long statId, String lang);
    String deleteByIdStatement(long statId);

}
