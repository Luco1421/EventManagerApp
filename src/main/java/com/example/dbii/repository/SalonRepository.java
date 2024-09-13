package com.example.dbii.repository;

import com.example.dbii.entity.Salon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends CrudRepository<Salon, Long> {
}
