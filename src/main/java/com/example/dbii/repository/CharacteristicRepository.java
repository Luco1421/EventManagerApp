package com.example.dbii.repository;

import com.example.dbii.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
    Optional<Characteristic> findByCharacteristicName(String name);
    Optional<Characteristic> findById(Long id);
    List<Characteristic> findAll();
}
