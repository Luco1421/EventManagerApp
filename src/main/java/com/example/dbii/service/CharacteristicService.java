package com.example.dbii.service;

import com.example.dbii.entity.Characteristic;
import com.example.dbii.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CharacteristicService {

    @Autowired
    private CharacteristicRepository characteristicRepository;

    public Characteristic getCharacteristicById(Long id) {
        return characteristicRepository.findById(id).orElse(null);
    }

    public Characteristic getCharacteristicByName(String name) {
        return characteristicRepository.findByCharacteristicName(name);
    }

    public List<Characteristic> getAllCharacteristic() {
        return characteristicRepository.findAll();
    }

    @Transactional
    public Long addFeature(String name) {
        if (characteristicRepository.findByCharacteristicName(name) != null) {
            return -1L;
        }
        Characteristic characteristic = new Characteristic();
        characteristic.setCharacteristicName(name);
        characteristicRepository.save(characteristic);
        return characteristic.getId();
    }

}
