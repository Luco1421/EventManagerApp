package com.example.dbii.service;

import com.example.dbii.entity.Reservation;
import com.example.dbii.entity.Salon;
import com.example.dbii.entity.UserE;
import com.example.dbii.repository.ReservationRepository;
import com.example.dbii.repository.SalonRepository;
import com.example.dbii.repository.UserERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@org.springframework.stereotype.Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private UserERepository userERepository;

    @Transactional
    public Long makeReservation(Long id, LocalDate dma, String email) {
        Reservation reservation = new Reservation();
        UserE user = userERepository.findByEmail(email);
        Salon salon = salonRepository.findById(id).orElse(null);
        reservation.setSalon(salon);
        reservation.setReservationDate(dma);
        reservation.setUser(user);
        reservation.setReservationState(-1L);
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateStatus(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        reservation.setReservationState(0L);
        reservationRepository.save(reservation);
    }

    @Transactional
    public void confirmReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        reservation.setReservationState(1L);
        reservationRepository.save(reservation);
    }
}
