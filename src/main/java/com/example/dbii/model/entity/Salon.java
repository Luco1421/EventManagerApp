package com.example.dbii.model.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "SALON")
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SALON_ID", nullable = false)
    private Long id;

    @Column(name = "SALON_NAME")
    private String salonName;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "MAX_CAPACITY")
    private Long maxCapacity;

    @OneToMany(mappedBy = "salon")
    private Set<Image> images = new LinkedHashSet<>();

    @OneToMany(mappedBy = "salon")
    private Set<Reservation> reservations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "salon")
    private Set<SalonCharacteristic> salonCharacteristics = new LinkedHashSet<>();

    public Set<SalonCharacteristic> getSalonCharacteristics() {
        return salonCharacteristics;
    }

    public void setSalonCharacteristics(Set<SalonCharacteristic> salonCharacteristics) {
        this.salonCharacteristics = salonCharacteristics;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

}