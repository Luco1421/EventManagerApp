package com.example.dbii.repository;

import com.example.dbii.entity.UserE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface UserERepository extends JpaRepository<UserE, Long> {
    UserE findByEmail(String email);
    @Procedure(procedureName = "CHECK_IF_EMPLOYEE")
    int checkIfEmployee(String email);
    @Procedure(procedureName = "checkuserreservations")
    int checkUserReservations(Long id);
    @Modifying
    void deleteById(Long id);
}
