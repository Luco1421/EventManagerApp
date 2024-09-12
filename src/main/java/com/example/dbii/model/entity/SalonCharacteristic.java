package com.example.dbii.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "SALON_CHARACTERISTIC")
public class SalonCharacteristic {

    @EmbeddedId
    private SalonCharacteristicKey id = new SalonCharacteristicKey();

    @MapsId("salonId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "SALON_ID", nullable = false)
    private Salon salon;

    @MapsId("characteristicId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CHARACTERISTIC_ID", nullable = false)
    private Characteristic characteristic;

    @Column(name = "QUANTITY")
    private int quantity;

    public SalonCharacteristicKey getId() {
        return id;
    }

    public void setId(SalonCharacteristicKey id) {
        this.id = id;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}