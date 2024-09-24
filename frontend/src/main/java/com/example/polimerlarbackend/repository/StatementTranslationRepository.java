package com.example.polimerlarbackend.repository;


import com.example.polimerlarbackend.model.StatementTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface StatementTranslationRepository extends JpaRepository<StatementTranslation, Long> {
    Optional<List<StatementTranslation>> findByCouncil_IdAndLanguage(long councilId,String lang);
    Optional<StatementTranslation> findByStatement_IdAndLanguage(long statId, String lang);
}
