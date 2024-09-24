package com.example.polimerlarbackend.repository;

import com.example.polimerlarbackend.model.NewsTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface NewsTranslationRepository extends JpaRepository<NewsTranslation, Long> {
    Optional<List<NewsTranslation>> findByLanguage(String lang);
    Optional<NewsTranslation> findByNews_idAndLanguage(long newsId, String lang);

}
