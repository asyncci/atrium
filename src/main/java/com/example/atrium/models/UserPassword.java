package com.example.atrium.models;

import jakarta.persistence.*;

@Entity
@Table
public class UserPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 250)
    private String password;

    @OneToOne()
    private User user;

    public UserPassword(String password, User user) {
        this.password = password;
        this.user = user;
    }
    public UserPassword(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
