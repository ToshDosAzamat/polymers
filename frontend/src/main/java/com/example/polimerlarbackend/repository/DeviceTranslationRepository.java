package com.example.polimerlarbackend.repository;

import com.example.polimerlarbackend.model.DeviceTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface DeviceTranslationRepository extends JpaRepository<DeviceTranslation, Long> {
    Optional<List<DeviceTranslation>> findByLab_idAndLanguage(long labId,String lang);
    Optional<DeviceTranslation> findByDevice_IdAndLanguage(long deviceId, String lang);
}
