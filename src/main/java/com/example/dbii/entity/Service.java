package com.example.dbii.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "SERVICE")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERVICE_ID", nullable = false)
    private Long id;

    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @Column(name = "SERVICE_DESCRIPTION")
    private String serviceDescription;

    @Column(name = "SERVICE_PRICE")
    private Double servicePrice;

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    private Set<Pack> packs = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    private Set<Reservation> reservations = new LinkedHashSet<>();

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Pack> getPacks() {
        return packs;
    }

    public void setPacks(Set<Pack> packs) {
        this.packs = packs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
    }
}