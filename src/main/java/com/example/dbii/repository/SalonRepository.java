package com.example.dbii.repository;

import com.example.dbii.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {
    Optional<Salon> findBySalonNameAndLocation(String salonName, String location);
}
