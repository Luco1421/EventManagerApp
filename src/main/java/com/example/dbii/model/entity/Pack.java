package com.example.dbii.model.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PACK")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PACK_ID", nullable = false)
    private Long id;

    @Column(name = "PACK_NAME")
    private String packName;

    @Column(name = "PACK_DESCRIPTION")
    private String packDescription;

    @Column(name = "PACK_PRICE")
    private Long packPrice;

    @ManyToMany
    @JoinTable(name = "PACK_SERVICE",
            joinColumns = @JoinColumn(name = "PACK_ID"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_ID"))
    private Set<Service> services = new LinkedHashSet<>();

    @OneToMany(mappedBy = "pack")
    private Set<Reservation> reservations = new LinkedHashSet<>();

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getPackDescription() {
        return packDescription;
    }

    public void setPackDescription(String packDescription) {
        this.packDescription = packDescription;
    }

    public Long getPackPrice() {
        return packPrice;
    }

    public void setPackPrice(Long packPrice) {
        this.packPrice = packPrice;
    }

}