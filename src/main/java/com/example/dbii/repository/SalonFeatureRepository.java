package com.example.dbii.repository;

import com.example.dbii.entity.Salon;
import com.example.dbii.entity.SalonCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalonFeatureRepository extends JpaRepository<SalonCharacteristic, Long> {
    Optional<SalonCharacteristic> findById(Long id);
    void deleteBySalon(Salon salon);
}
