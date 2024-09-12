package com.example.dbii.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Characteristic implements Serializable {
    private static final long serialVersionUID = -1626215443898586764L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @Column
    private String name;

    @Column
    private int quantity;

    @ManyToMany(mappedBy = "characteristics")
    private Set<Salon> salons = new HashSet<>();

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Salon> getSalons() {
        return salons;
    }

    public void setSalons(Set<Salon> salons) {
        this.salons = salons;
    }

    @Override
    public String toString() {
        return "Characteristic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", salons=" + salons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characteristic that = (Characteristic) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(salons, that.salons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, salons);
    }
}
