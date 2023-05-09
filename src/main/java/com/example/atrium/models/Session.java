package com.example.atrium.models;

import jakarta.persistence.*;

@Entity
@Table
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100,unique = true)
    private String ipAddress;

    @OneToOne()
    private User user;

    public Session(String ipAddress, User user) {
        this.ipAddress = ipAddress;
        this.user = user;
    }

    public Session() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
