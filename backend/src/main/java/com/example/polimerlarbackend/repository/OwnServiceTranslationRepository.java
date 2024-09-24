package com.example.polimerlarbackend.repository;

import com.example.polimerlarbackend.model.OwnServiceTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface OwnServiceTranslationRepository extends JpaRepository<OwnServiceTranslation, Long> {
    Optional<List<OwnServiceTranslation>> findByLanguage(String lang);
    Optional<List<OwnServiceTranslation>> findByOwnService_Id(long id);
}
