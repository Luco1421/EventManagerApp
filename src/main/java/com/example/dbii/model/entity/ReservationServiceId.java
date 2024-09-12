package com.example.dbii.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class ReservationServiceId implements java.io.Serializable {
    private static final long serialVersionUID = 9041400622050589392L;
    @Column(name = "RESERVATION_ID", nullable = false)
    private Long reservationId;

    @Column(name = "SERVICE_ID", nullable = false)
    private Long serviceId;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReservationServiceId entity = (ReservationServiceId) o;
        return Objects.equals(this.reservationId, entity.reservationId) &&
                Objects.equals(this.serviceId, entity.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, serviceId);
    }

}