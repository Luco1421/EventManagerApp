package com.example.dbii.repository;

import com.example.dbii.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Optional<Service> findById(Long id);
    Optional<Service> findByServiceName(String name);
    List<Service> findAll();
}
