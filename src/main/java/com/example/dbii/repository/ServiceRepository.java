package com.example.dbii.repository;

import com.example.dbii.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Optional<Service> findById(Long id);
    Optional<Service> findByServiceName(String name);
    List<Service> findAll();
    @Modifying
    @Query(value = "DELETE FROM PACK_SERVICE WHERE PACK_ID = :packId AND SERVICE_ID = :serviceId", nativeQuery = true)
    void deleteServiceFromPack(@Param("packId") Long packId, @Param("serviceId") Long serviceId);
}
