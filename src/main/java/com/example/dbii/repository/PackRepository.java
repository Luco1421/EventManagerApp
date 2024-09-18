package com.example.dbii.repository;

import com.example.dbii.entity.Pack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PackRepository extends JpaRepository<Pack, Long> {
    Pack findByPackName(String name);
    @Query(value = "SELECT * FROM Pack WHERE Pack.PACK_NAME LIKE %:name% or UTL_MATCH.EDIT_DISTANCE(Pack.PACK_NAME, :name) < 15", nativeQuery = true)
    Set<Pack> findLikeNameSound(String name);
    @Procedure(procedureName = "checkpackreservations")
    int checkpackreservations(Long id);
}

/*@Modifying
    @Query(value = "INSERT INTO PACK_SERVICE (PACK_ID, SERVICE_ID) VALUES (:packId, :serviceId)", nativeQuery = true)
    void addServiceToPack(@Param("packId") Long packId, @Param("serviceId") Long serviceId);
    @Modifying
    @Query(value = "DELETE FROM PACK_SERVICE WHERE PACK_ID = :packId AND SERVICE_ID = :serviceId", nativeQuery = true)
    void removeServiceFromPack(@Param("packId") Long packId, @Param("serviceId") Long serviceId);*/
