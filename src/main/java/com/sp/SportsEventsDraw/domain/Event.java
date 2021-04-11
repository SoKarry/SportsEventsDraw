package com.sp.SportsEventsDraw.domain;

import javax.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    public Event() {
    }

    public Event(String name, User user) {
        this.name = name;
        this.owner = user;
    }

    public String getOwnerName() {
        return owner != null ? owner.getUsername() : "<not find>";
    }

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
