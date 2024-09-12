package com.example.dbii.model.entity;

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

    @OneToMany(mappedBy = "characteristic")
    private Set<SalonCharacteristic> salonCharacteristics = new LinkedHashSet<>();

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

    public Set<SalonCharacteristic> getSalonCharacteristics() {
        return salonCharacteristics;
    }

    public void setSalonCharacteristics(Set<SalonCharacteristic> salonCharacteristics) {
        this.salonCharacteristics = salonCharacteristics;
    }

}