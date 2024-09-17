package com.example.dbii.service;

import com.example.dbii.entity.Salon;
import com.example.dbii.repository.ImageRepository;
import com.example.dbii.repository.SalonFeatureRepository;
import com.example.dbii.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service
public class SalonService {

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private SalonFeatureRepository salonFeatureRepository;

    public Salon getSalonById(Long id) {
        return salonRepository.findById(id).get();
    }

    public Set<Salon> getSalonByName(String name) {
        return salonRepository.findLikeNameSound(name);
    }

    public Set<Salon> getAvailableSalons(LocalDate reservationDate) {
        return salonRepository.findAvailableSalons(reservationDate);
    }

    public boolean isDropeable(Long salonId) {
        return salonRepository.CheckSalonReservations(salonId) == 0;
    }

    @Transactional
    public Long addSalon(String name, String location, Long maxCapacity) {
        if (salonRepository.findBySalonNameAndLocation(name, location).isPresent()) {
            return -1L;
        }
        Salon salon = new Salon();
        salon.setSalonName(name);
        salon.setLocation(location);
        salon.setMaxCapacity(maxCapacity);

        salonRepository.save(salon);

        return salon.getId();
    }

    @Transactional
    public void deleteSalon(Long salonId) {
        if (salonRepository.findById(salonId).isPresent() && isDropeable(salonId)) {
            Salon salon = salonRepository.findById(salonId).get();
            imageRepository.deleteBySalon(salon);
            salonFeatureRepository.deleteBySalon(salon);
            salonRepository.delete(salon);
        }
    }
}
