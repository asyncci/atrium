package com.example.atrium.models;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 150,unique = true)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private UserPassword userPassword;

    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private Session session;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
