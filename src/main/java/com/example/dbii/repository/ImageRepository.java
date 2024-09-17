package com.example.dbii.repository;

import com.example.dbii.entity.Image;
import com.example.dbii.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    void deleteBySalon(Salon salon);
}
