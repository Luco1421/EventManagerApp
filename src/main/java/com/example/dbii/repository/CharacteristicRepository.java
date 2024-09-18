package com.example.dbii.repository;

import com.example.dbii.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
    Characteristic findByCharacteristicName(String name);
    @Procedure("obtener_caracteristicas_recurrentes")
    String obtenerCaracteristicasRecurrentes();
}
