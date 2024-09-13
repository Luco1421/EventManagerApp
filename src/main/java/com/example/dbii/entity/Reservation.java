package com.example.dbii.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "PACK_ID")
    private Pack pack;

    @Column(name = "RESERVATION_STATE")
    private Long reservationState;

    @Column(name = "RESERVATION_DATE")
    private LocalDate reservationDate;

    @Column(name = "RESERVATION_PRICE")
    private Long reservationPrice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RESERVATION_SERVICE",
            joinColumns = @JoinColumn(name = "RESERVATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_ID"))
    private Set<Service> services = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALON_ID")
    private Salon salon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID")
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserE user;

    public UserE getUser() {
        return user;
    }

    public void setUser(UserE user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public Long getReservationState() {
        return reservationState;
    }

    public void setReservationState(Long reservationState) {
        this.reservationState = reservationState;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Long getReservationPrice() {
        return reservationPrice;
    }

    public void setReservationPrice(Long reservationPrice) {
        this.reservationPrice = reservationPrice;
    }

}