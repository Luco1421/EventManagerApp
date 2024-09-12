package com.example.dbii.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Pack implements Serializable {
    private static final long serialVersionUID = 1671508238369069884L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Double price;

    @ManyToMany
    @JoinTable(
            name = "pack_service",
            joinColumns = @JoinColumn(name = "pack_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )

    private Set<Service> services = new HashSet<>();

    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", services=" + services +
                ", reservations=" + reservations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pack pack = (Pack) o;
        return id == pack.id && Objects.equals(name, pack.name) && Objects.equals(description, pack.description) && Objects.equals(price, pack.price) && Objects.equals(services, pack.services) && Objects.equals(reservations, pack.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, services, reservations);
    }
}
