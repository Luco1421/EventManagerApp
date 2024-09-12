package com.example.dbii.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Salon implements Serializable {
    private static final long serialVersionUID = -8735461337447986136L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @Column
    private String name;

    @Column
    private String location;

    @Column
    private int maxCapacity;

    @OneToMany(mappedBy = "salon")
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "salon_characterist",
            joinColumns = @JoinColumn(name = "salon_id"),
            inverseJoinColumns = @JoinColumn(name = "characterist_id")
    )

    private Set<Characteristic> characteristics = new HashSet<>();

    @OneToMany(mappedBy = "images", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", reservations=" + reservations +
                ", characteristics=" + characteristics +
                ", images=" + images +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salon salon = (Salon) o;
        return maxCapacity == salon.maxCapacity && Objects.equals(id, salon.id) && Objects.equals(name, salon.name) && Objects.equals(location, salon.location) && Objects.equals(reservations, salon.reservations) && Objects.equals(characteristics, salon.characteristics) && Objects.equals(images, salon.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, maxCapacity, reservations, characteristics, images);
    }
}
