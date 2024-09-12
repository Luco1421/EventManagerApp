package com.example.dbii.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "SALON_CHARACTERISTIC")
public class SalonCharacteristic {
    @EmbeddedId
    private SalonCharacteristicId id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CHARACTERISTIC_ID", nullable = false)
    private Characteristic characteristic;

    @Column(name = "QUANTITY")
    private Long quantity;

    public SalonCharacteristicId getId() {
        return id;
    }

    public void setId(SalonCharacteristicId id) {
        this.id = id;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}