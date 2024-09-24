package com.example.polimerlarbackend.repository;


import com.example.polimerlarbackend.model.CouncilTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface CouncilTranslationRepository extends JpaRepository<CouncilTranslation, Long> {
    Optional<List<CouncilTranslation>> findByLanguage(String lang);
    Optional<List<CouncilTranslation>> findByCouncil_Id(long councilId);
    Optional<CouncilTranslation> findByCouncil_IdAndLanguage(long councilId, String lang);
}
