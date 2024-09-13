package com.example.dbii.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "USERE")
public class UserE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE")
    private Long phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ISEMPLOYEE")
    private Long isemployee;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIsemployee() {
        return isemployee;
    }

    public void setIsemployee(Long isemployee) {
        this.isemployee = isemployee;
    }

}