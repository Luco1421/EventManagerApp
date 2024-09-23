package com.example.dbii.repository;

import com.example.dbii.entity.Reservation;
import com.example.dbii.entity.UserE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.user = :user ORDER BY r.reservationDate DESC")
    List<Reservation> findReservationsForUser(@Param("user") UserE user);
    @Modifying
    void deleteById(Long id);
}