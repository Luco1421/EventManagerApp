package com.example.dbii.service;

import com.example.dbii.entity.Characteristic;
import com.example.dbii.repository.CharacteristicRepository;
import com.example.dbii.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacteristicService {

    @Autowired
    private CharacteristicRepository characteristicRepository;

    public Characteristic getCharacteristicById(Long id) {
        return characteristicRepository.findById(id).get();
    }

    public Characteristic getCharacteristicByName(String name) {
        return characteristicRepository.findByCharacteristicName(name).get();
    }

    public List<Characteristic> getAllCharacteristic() {
        return characteristicRepository.findAll();
    }

    @Transactional
    public Long addFeature(String name) {
        if (characteristicRepository.findByCharacteristicName(name).isPresent()) {
            return -1L;
        }
        Characteristic characteristic = new Characteristic();
        characteristic.setCharacteristicName(name);

        characteristicRepository.save(characteristic);
        return characteristic.getId();
    }

    @Transactional
    public void deletePack(String name) {
        //
    }

}
