package com.example.dbii.repository;

import com.example.dbii.entity.UserE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserERepository extends CrudRepository<UserE, Long> {
}
