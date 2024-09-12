package com.example.dbii.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class PackServiceId implements java.io.Serializable {
    private static final long serialVersionUID = 6754298594026188329L;
    @Column(name = "PACK_ID", nullable = false)
    private Long packId;

    @Column(name = "SERVICE_ID", nullable = false)
    private Long serviceId;

    public Long getPackId() {
        return packId;
    }

    public void setPackId(Long packId) {
        this.packId = packId;
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
        PackServiceId entity = (PackServiceId) o;
        return Objects.equals(this.packId, entity.packId) &&
                Objects.equals(this.serviceId, entity.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packId, serviceId);
    }

}