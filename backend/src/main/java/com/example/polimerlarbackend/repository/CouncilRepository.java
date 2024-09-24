package com.example.polimerlarbackend.repository;

import com.example.polimerlarbackend.model.Council;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouncilRepository extends JpaRepository<Council, Long> {
    boolean deleteById(long councilId);
}
