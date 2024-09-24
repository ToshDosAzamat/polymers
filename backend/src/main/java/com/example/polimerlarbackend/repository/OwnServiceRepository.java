package com.example.polimerlarbackend.repository;

import com.example.polimerlarbackend.model.OwnService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnServiceRepository extends JpaRepository<OwnService, Long> {
    boolean deleteById(long id);
}
