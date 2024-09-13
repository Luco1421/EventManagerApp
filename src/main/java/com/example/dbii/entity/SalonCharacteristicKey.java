package com.example.dbii.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class SalonCharacteristicKey implements Serializable {
    private Long salonId;
    private Long characteristicId;

    public SalonCharacteristicKey() {}

    public SalonCharacteristicKey(Long salonId, Long characteristicId) {
        this.salonId = salonId;
        this.characteristicId = characteristicId;
    }

    public Long getSalonId() {
        return salonId;
    }

    public void setSalonId(Long salonId) {
        this.salonId = salonId;
    }

    public Long getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(Long characteristicId) {
        this.characteristicId = characteristicId;
    }
}