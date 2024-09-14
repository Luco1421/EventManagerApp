package com.example.dbii.service;

import com.example.dbii.entity.Salon;
import com.example.dbii.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Transactional
    public void makeReservation() throws Exception {
    }

}
