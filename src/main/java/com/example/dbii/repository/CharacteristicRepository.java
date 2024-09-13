package com.example.dbii.repository;

import com.example.dbii.entity.Characteristic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends CrudRepository<Characteristic, Long> {
}
