package com.example.dbii.repository;

import com.example.dbii.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {
    @Query(value = "SELECT * FROM Salon WHERE Salon.SALON_NAME LIKE %:name% or UTL_MATCH.EDIT_DISTANCE(Salon.SALON_NAME, :name) < 15", nativeQuery = true)
    Set<Salon> findLikeNameSound(String name);
    Optional<Salon> findBySalonNameAndLocation(String name, String location);

}
