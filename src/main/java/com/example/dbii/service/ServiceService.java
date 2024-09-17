package com.example.dbii.service;

import com.example.dbii.entity.Pack;
import com.example.dbii.repository.PackRepository;
import com.example.dbii.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public com.example.dbii.entity.Service getServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId).get();
    }

    public com.example.dbii.entity.Service getServiceByName(String name) {
        return serviceRepository.findByServiceName(name).get();
    }

    @Transactional
    public Long addService(String name, String description, Double price) {
        if (serviceRepository.findByServiceName(name).isPresent()) return -1L;
        com.example.dbii.entity.Service service = new com.example.dbii.entity.Service();
        service.setServiceName(name);
        service.setServiceDescription(description);
        service.setServicePrice(price);
        serviceRepository.save(service);
        return service.getId();
    }

    /*@Transactional
    public void addServiceToPack(Long packId, Long serviceId) {
        Pack pack = packRepository.findById(packId).get();
        com.example.dbii.entity.Service service = serviceRepository.findById(serviceId).get();
        pack.getServices().add(service);
        packRepository.save(pack);
    }

    @Transactional
    public void removeServiceFromPack(Long packId, Long serviceId) {
        Pack pack = packRepository.findById(packId).get();
        com.example.dbii.entity.Service service = serviceRepository.findById(serviceId).get();
        pack.getServices().remove(service);
        packRepository.save(pack);
    }*/

    public List<com.example.dbii.entity.Service> getAllServices() {
        return serviceRepository.findAll();
    }
}
