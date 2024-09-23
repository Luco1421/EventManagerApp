package com.example.dbii.repository;

import com.example.dbii.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
    Characteristic findByCharacteristicName(String name);
    @Query(value = "SELECT OBTENER_CARACTERISTICA_RECURRENTE FROM DUAL", nativeQuery = true)
    String OBTENER_CARACTERISTICA_RECURRENTE();
    @Query(value = "SELECT Obtener_Salon_y_Evento_Mas_Comun(:fechaInicio, :fechaFin) FROM DUAL", nativeQuery = true)
    String Obtener_Salon_y_Evento_Mas_Comun(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
}
