package com.example.dbii.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CHARACTERISTIC")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CHARACTERISTIC_ID", nullable = false)
    private Long id;

    @Column(name = "CHARACTERISTIC_NAME")
    private String characteristicName;

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