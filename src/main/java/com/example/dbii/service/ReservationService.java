package com.example.dbii.service;

import com.example.dbii.entity.*;
import com.example.dbii.repository.ReservationRepository;
import com.example.dbii.repository.SalonRepository;
import com.example.dbii.repository.TypeRepository;
import com.example.dbii.repository.UserERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private UserERepository userERepository;

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    public Reservation makeReservation(Salon salon, LocalDate dma, UserE user, Type type) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(dma);
        reservation.setReservationPrice(0.0);
        reservation.setReservationState(-1L);
        reservation.setSalon(salon);
        reservation.setType(type);
        reservation.setUser(user);
        reservationRepository.save(reservation);
        return reservation;
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Transactional
    public void inProcess(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        reservation.setReservationState(0L);
        reservationRepository.save(reservation);
    }

    public List<Reservation> findReservationsByUser(UserE user) {
        return reservationRepository.findReservationsForUser(user);
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    /*@Transactional
    public void confirmReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        reservation.setReservationState(1L);
        reservationRepository.save(reservation);
    }*/
}
