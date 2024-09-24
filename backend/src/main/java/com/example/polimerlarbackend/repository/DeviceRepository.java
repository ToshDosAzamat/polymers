package com.example.polimerlarbackend.repository;

import com.example.polimerlarbackend.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    void deleteById(long deviceId);
}
