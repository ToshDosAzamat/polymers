package com.example.polimerlarbackend.repository;


import com.example.polimerlarbackend.model.AdministrationTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AdministrationTranslationRepository extends JpaRepository<AdministrationTranslation, Long> {
    Optional<List<AdministrationTranslation>> findByLanguage(String lang);
    Optional<List<AdministrationTranslation>> findByAdministration_Id(long AdminiId);
}
