package com.example.dbii.service;

import com.example.dbii.entity.Salon;
import com.example.dbii.repository.ImageRepository;
import com.example.dbii.repository.SalonFeatureRepository;
import com.example.dbii.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
        return salonRepository.findById(id).orElse(null);
    }

    public Set<Salon> getSalonByName(String name) {
        return salonRepository.findLikeNameSound(name);
    }

    public List<Salon> getAvailableSalons(LocalDate reservationDate) {
        return salonRepository.findAvailableSalons(reservationDate);
    }

    @Transactional
    public Long addSalon(String name, String location, Long maxCapacity) {
        if (salonRepository.findBySalonName(name) != null) {
            return -1L;
        }
        Salon salon = new Salon();
        salon.setSalonName(name);
        salon.setLocation(location);
        salon.setMaxCapacity(maxCapacity);

        salonRepository.save(salon);

        return salon.getId();
    }

    public boolean isDropeable(Long salonId) {
        return salonRepository.CheckSalonReservations(salonId) == 0;
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

    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    public String getSalonYEventoMasComun(Date fechaInicio, Date fechaFin) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicioStr = sdf.format(fechaInicio);
        String fechaFinStr = sdf.format(fechaFin);
        return salonRepository.obtenerSalonYEventoMasComun(fechaInicioStr, fechaFinStr);
    }

    public Salon updateSalon(Long id, String name, String location, Long maxCapacity) {
        Salon salon = getSalonById(id);
        if (salon != null) {
            salon.setSalonName(name);
            salon.setLocation(location);
            salon.setMaxCapacity(maxCapacity);
            return salonRepository.save(salon);
        }
        return null;
    }
}
