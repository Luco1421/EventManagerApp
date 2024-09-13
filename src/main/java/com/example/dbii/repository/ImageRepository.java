package com.example.dbii.repository;

import com.example.dbii.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
}
