package com.example.dbii.service;

import com.example.dbii.entity.Pack;
import com.example.dbii.entity.Salon;
import com.example.dbii.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class PackService {

    @Autowired
    private PackRepository packRepository;

    @Transactional
    public Long addPack(String name, String description) {
        if (packRepository.findByPackName(name).isPresent()) {
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
        Pack pack = packRepository.findById(id).get();
        return pack;
    }

    public Set<Pack> getSalonByName(String name) {
        return packRepository.findLikeNameSound(name);
    }

    @Transactional
    public void deletePack(String name) {
        //
    }

}
