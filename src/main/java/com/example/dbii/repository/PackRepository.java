package com.example.dbii.repository;

import com.example.dbii.entity.Pack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PackRepository extends JpaRepository<Pack, Long> {
    Optional<Pack> findByPackName(String name);
    @Query(value = "SELECT * FROM Pack WHERE Pack.PACK_NAME LIKE %:name% or UTL_MATCH.EDIT_DISTANCE(Pack.PACK_NAME, :name) < 15", nativeQuery = true)
    Set<Pack> findLikeNameSound(String name);
    Optional<Pack> findById(Long id);
    @Procedure(procedureName = "checkpackreservations")
    int checkpackreservations(Long id);
}
