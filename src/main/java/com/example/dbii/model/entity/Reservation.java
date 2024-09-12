package com.example.dbii.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Reservation implements Serializable {
    private static final long serialVersionUID = 7262272579910540298L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "salon_id")
    private Salon salon;

    @ManyToOne
    @JoinColumn(name = "event_type_id")
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "pack_id")
    private Pack pack;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String state;

    @Column
    private Date date;

    @Column
    private Double price;

    @ManyToMany
    @JoinTable(
            name = "reservation_service",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )

    private Set<Service> services = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", salon=" + salon +
                ", eventType=" + eventType +
                ", pack=" + pack +
                ", user=" + user +
                ", state='" + state + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", services=" + services +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) && Objects.equals(salon, that.salon) && Objects.equals(eventType, that.eventType) && Objects.equals(pack, that.pack) && Objects.equals(user, that.user) && Objects.equals(state, that.state) && Objects.equals(date, that.date) && Objects.equals(price, that.price) && Objects.equals(services, that.services);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salon, eventType, pack, user, state, date, price, services);
    }
}
