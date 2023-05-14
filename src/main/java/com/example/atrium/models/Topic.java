package com.example.atrium.models;

import jakarta.persistence.*;

@Entity
@Table
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(length = 300)
    private String title;

    public Topic(User user, String title, String argument) {
        this.user = user;
        this.title = title;
    }

    public Topic(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
