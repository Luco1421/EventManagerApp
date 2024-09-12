package com.example.dbii.model.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"TYPE\"")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TYPE_ID", nullable = false)
    private Long id;

    @Column(name = "TYPE_NAME")
    private String typeName;

    @OneToMany(mappedBy = "type")
    private Set<Reservation> reservations = new LinkedHashSet<>();

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}