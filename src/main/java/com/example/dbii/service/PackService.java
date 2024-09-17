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
        return packRepository.findById(id).get();
    }

    public Set<Pack> getPackByName(String name) {
        return packRepository.findLikeNameSound(name);
    }

    public boolean isDropeable(Long packId) {
        return packRepository.checkpackreservations(packId) == 0;
    }

    @Transactional
    public void deletePack(Long packId) {
        if (packRepository.findById(packId).isPresent() && isDropeable(packId)) packRepository.deleteById(packId);
    }

}
