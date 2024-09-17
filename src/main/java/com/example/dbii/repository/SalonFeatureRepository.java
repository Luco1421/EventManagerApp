package com.example.dbii.repository;

import com.example.dbii.entity.Salon;
import com.example.dbii.entity.SalonCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SalonFeatureRepository extends JpaRepository<SalonCharacteristic, Long> {
    Optional<SalonCharacteristic> findById(Long id);
    void deleteBySalon(Salon salon);
    @Modifying
    @Query(value = "DELETE FROM SalonCharacteristic sc WHERE sc.salon.id = :salonId AND sc.characteristic.id = :characteristicId")
    void deleteBySalonIdAndCharacteristicId(@Param("salonId") Long salonId, @Param("characteristicId") Long characteristicId);
}
