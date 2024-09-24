package com.example.polimerlarbackend.repository;

import com.example.polimerlarbackend.model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {
    Optional<Lab> findById(long labId);
    void deleteById(long labId);
}
