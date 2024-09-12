package com.example.dbii.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Image implements Serializable {
    private static final long serialVersionUID = -7855374658515345216L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "salon_id")
    private Salon salon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", salon=" + salon +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(url, image.url) && Objects.equals(salon, image.salon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, salon);
    }
}
