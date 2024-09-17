package com.example.dbii.service;

import com.example.dbii.entity.Pack;
import com.example.dbii.entity.Service;
import com.example.dbii.repository.PackRepository;
import com.example.dbii.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public Service getServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId).get();
    }

    public Service getServiceByName(String name) {
        if (serviceRepository.findByServiceName(name).isPresent())
            return serviceRepository.findByServiceName(name).get();
        else return null;
    }

    @Transactional
    public Long addService(String name, String description, Double price) {
        if (serviceRepository.findByServiceName(name).isPresent()) return -1L;
        Service service = new Service();
        service.setServiceName(name);
        service.setServiceDescription(description);
        service.setServicePrice(price);
        serviceRepository.save(service);
        return service.getId();
    }

    @Transactional
    public void addServiceToPack(Long packId, Long serviceId) {
        Pack pack = packRepository.findById(packId).get();
        Service service = getServiceById(serviceId);

        pack.getServices().add(service);
        service.getPacks().add(pack);

        packRepository.save(pack);
        serviceRepository.save(service);
    }

    @Transactional
    public void removeServiceFromPack(Long packId, Long serviceId) {
        Pack pack = packRepository.findById(packId).get();
        Service service = getServiceById(serviceId);

        pack.getServices().remove(service);
        service.getPacks().remove(pack);

        packRepository.save(pack);
        serviceRepository.save(service);
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }
}
