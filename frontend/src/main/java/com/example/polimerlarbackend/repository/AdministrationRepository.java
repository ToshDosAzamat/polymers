package com.example.polimerlarbackend.repository;


import com.example.polimerlarbackend.model.Administration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrationRepository extends JpaRepository<Administration, Long> {
    boolean deleteById(long adminiId);
}
