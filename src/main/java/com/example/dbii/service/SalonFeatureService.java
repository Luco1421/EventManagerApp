package com.example.dbii.service;

import com.example.dbii.entity.Characteristic;
import com.example.dbii.entity.Salon;
import com.example.dbii.entity.SalonCharacteristic;
import com.example.dbii.entity.SalonCharacteristicKey;
import com.example.dbii.repository.CharacteristicRepository;
import com.example.dbii.repository.SalonRepository;
import com.example.dbii.repository.SalonFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalonFeatureService {

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Autowired
    private SalonFeatureRepository salonFeatureRepository;

    @Transactional
    public void addFeatureToSalon(Long salonId, Long characteristicId, int quantity) {
        Salon salon = salonRepository.findById(salonId).orElse(null);
        Characteristic characteristic = characteristicRepository.findById(characteristicId).orElse(null);

        SalonCharacteristicKey key = new SalonCharacteristicKey(salonId, characteristicId);

        SalonCharacteristic salonCharacteristic = new SalonCharacteristic();

        salonCharacteristic.setId(key);
        salonCharacteristic.setSalon(salon);
        salonCharacteristic.setCharacteristic(characteristic);
        salonCharacteristic.setQuantity(quantity);

        salonFeatureRepository.save(salonCharacteristic);
    }

    @Transactional
    public void removeFeatureFromSalon(Long salonId, Long characteristicId) {
        salonFeatureRepository.deleteBySalonIdAndCharacteristicId(salonId, characteristicId); //
    }

}
