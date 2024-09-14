package com.example.dbii.service;

import com.example.dbii.entity.Salon;
import com.example.dbii.entity.UserE;
import com.example.dbii.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SalonService {

    @Autowired
    private SalonRepository salonRepository;

    public void addSalon(String name, String location, Long maxCapacity) throws Exception {
        if (salonRepository.findBySalonNameAndLocation(name, location).isPresent()) {
            throw new Exception("Ya este salón está registrado");
        }
        Salon salon = new Salon();
        salon.setSalonName(name);
        salon.setLocation(location);
        salon.setMaxCapacity(maxCapacity);

        salonRepository.save(salon);
    }

    public int processLogin(String email, String password) {
        Optional<UserE> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isEmpty()) { return 0; }
        if (!userByEmail.get().getPassword().equals(password)) { return 50; }
        return 100;
    }

}
