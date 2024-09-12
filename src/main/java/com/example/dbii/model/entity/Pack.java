package com.example.dbii.model.entity;

import jakarta.persistence.*;

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