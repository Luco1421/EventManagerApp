package com.example.dbii.repository;

import com.example.dbii.entity.Salon;
import com.example.dbii.entity.UserE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserERepository extends JpaRepository<UserE, Long> {
    Optional<UserE> findByEmail(String email);
    Set<UserE> findAllByEmail(String email);
    Optional<UserE> findById(Long id);
    @Procedure(procedureName = "CHECK_IF_EMPLOYEE")
    int checkIfEmployee(String email);
    void deleteById(Long userId);
    @Procedure(procedureName = "checkuserreservations")
    int checkUserReservations(Long id);
}
