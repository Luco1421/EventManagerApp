package com.example.dbii.repository;

import com.example.dbii.entity.Pack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackRepository extends CrudRepository<Pack, Long> {
}
