package com.example.dbii.repository;

import com.example.dbii.entity.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
}
