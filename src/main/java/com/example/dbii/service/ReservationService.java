package com.example.dbii.service;

import com.example.dbii.entity.Reservation;
import com.example.dbii.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation makeReservation() {
        Reservation reservation = new Reservation();
        return reservationRepository.save(reservation);
    }
}
