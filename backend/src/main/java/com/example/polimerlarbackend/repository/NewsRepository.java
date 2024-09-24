package com.example.polimerlarbackend.repository;

import com.example.polimerlarbackend.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    boolean deleteById(long id);
}
