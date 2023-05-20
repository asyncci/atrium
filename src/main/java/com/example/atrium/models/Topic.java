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

    @Column(length = 1500)
    private String argument;


    public Topic(User user, String title, String argument) {
        this.user = user;
        this.title = title;
        this.argument = argument;
    }

    public Topic(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
