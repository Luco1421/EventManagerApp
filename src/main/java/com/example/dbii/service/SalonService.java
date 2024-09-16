package com.example.dbii.service;

import com.example.dbii.entity.Salon;
import com.example.dbii.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class SalonService {

    @Autowired
    private SalonRepository salonRepository;

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

    public Salon getSalonById(Long id) {
        Salon salon = salonRepository.findById(id).get();
        return salon;
    }

    public Set<Salon> getSalonByName(String name) {
        return salonRepository.findLikeNameSound(name);
    }

    @Transactional
    public void deleteSalon(String name, String location) throws Exception {
        if (!salonRepository.findBySalonNameAndLocation(name, location).isPresent()) {
            throw new Exception("Este salón no existe");
        }
        //
    }

}
