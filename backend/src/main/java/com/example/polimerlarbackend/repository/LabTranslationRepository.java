package com.example.polimerlarbackend.repository;

import com.example.polimerlarbackend.model.LabTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface LabTranslationRepository extends JpaRepository<LabTranslation, Long> {
    Optional<Set<LabTranslation>> findByLanguage(String lang);
    Optional<LabTranslation> findByLab_idAndLanguage(long labId, String lang);
}
