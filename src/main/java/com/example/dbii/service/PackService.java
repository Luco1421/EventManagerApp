package com.example.dbii.service;

import com.example.dbii.entity.Pack;
import com.example.dbii.entity.Reservation;
import com.example.dbii.repository.PackRepository;
import com.example.dbii.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Service
public class PackService {

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    public Long addPack(String name, String description) {
        if (packRepository.findByPackName(name) != null) {
            return -1L;
        }
        Pack pack = new Pack();
        pack.setPackName(name);
        pack.setPackDescription(description);
        pack.setPackPrice(0.0);
        packRepository.save(pack);
        return pack.getId();
    }

    public Pack getPackById(Long id) {
        return packRepository.findById(id).orElse(null);
    }

    public Set<Pack> getPackByName(String name) {
        return packRepository.findLikeNameSound(name);
    }

    public List<Pack> getAllPacks() {
        return packRepository.findAll();
    }

    @Transactional
    public void addPackToReservation(Long reservationId, Long packId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        Pack pack = getPackById(packId);
        reservation.setPack(pack);
        reservationRepository.save(reservation);
    }

    public boolean isDropeable(Long packId) {
        return packRepository.checkpackreservations(packId) == 0;
    }

    @Transactional
    public void deletePack(Long packId) {
        if (packRepository.findById(packId).isPresent() && isDropeable(packId)) packRepository.deleteById(packId);
    }

}
