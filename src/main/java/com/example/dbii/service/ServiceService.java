package com.example.dbii.service;

import com.example.dbii.entity.Pack;
import com.example.dbii.entity.Reservation;
import com.example.dbii.entity.Service;
import com.example.dbii.repository.PackRepository;
import com.example.dbii.repository.ReservationRepository;
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

    @Autowired
    private ReservationRepository reservationRepository;

    public Service getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public Service getServiceByName(String name) {
        return serviceRepository.findByServiceName(name);
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Transactional
    public Long addService(String name, String description, Double price) {
        if (serviceRepository.findByServiceName(name) != null) {
            return -1L;
        }
        Service service = new Service();
        service.setServiceName(name);
        service.setServiceDescription(description);
        service.setServicePrice(price);
        serviceRepository.save(service);
        return service.getId();
    }

    @Transactional
    public void addServiceToPack(Long packId, Long serviceId) {
        Pack pack = packRepository.findById(packId).orElse(null);
        Service service = serviceRepository.findById(serviceId).orElse(null);
        pack.addService(service);
        packRepository.save(pack);
    }

    @Transactional
    public void removeServiceToPack(Long packId, Long serviceId) {
        Pack pack = packRepository.findById(packId).orElse(null);
        Service service = serviceRepository.findById(serviceId).orElse(null);
        pack.removeService(service);
        packRepository.save(pack);
    }

    @Transactional
    public void addServiceToReservation(Long reservationId, Long serviceId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        Service service = getServiceById(serviceId);
        reservation.getServices().add(service);
        reservationRepository.save(reservation);
    }

    @Transactional
    public void removeServiceFromReservation(Long reservationId, Long serviceId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        Service service = getServiceById(serviceId);
        reservation.getServices().remove(service);
        reservationRepository.save(reservation);
    }
}
