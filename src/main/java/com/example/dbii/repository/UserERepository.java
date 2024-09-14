package com.example.dbii.repository;

import com.example.dbii.entity.UserE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserERepository extends JpaRepository<UserE, Long> {
    Optional<UserE> findByEmail(String email);
}
