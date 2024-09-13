package com.example.dbii.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CHARACTERISTIC")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHARACTERISTIC_ID", nullable = false)
    private Long id;

    @Column(name = "CHARACTERISTIC_NAME")
    private String characteristicName;

    @OneToMany(mappedBy = "characteristic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SalonCharacteristic> salonCharacteristics = new LinkedHashSet<>();

    public Set<SalonCharacteristic> getSalonCharacteristics() {
        return salonCharacteristics;
    }

    public void setSalonCharacteristics(Set<SalonCharacteristic> salonCharacteristics) {
        this.salonCharacteristics = salonCharacteristics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }

}