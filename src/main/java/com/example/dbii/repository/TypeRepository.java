package com.example.dbii.repository;

import com.example.dbii.entity.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends CrudRepository<Type, Long> {
}
