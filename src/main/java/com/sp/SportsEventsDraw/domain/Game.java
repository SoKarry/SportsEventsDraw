package com.sp.SportsEventsDraw.domain;

import javax.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event evowner;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "player1_id")
    private Player player1;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "player2_id")
    private Player player2;

    public Game() {
    }

    public Game(Event evowner, Player player1, Player player2) {
        this.evowner = evowner;
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getPlayer1Name() {
        return player1 != null ? player1.getPlname() : "<not find>";
    }

    public String getPlayer2Name() {
        return player2 != null ? player2.getPlname() : "<not find>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvowner() {
        return evowner;
    }

    public void setEvowner(Event evowner) {
        this.evowner = evowner;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public String getEvOwnerName() {
        return evowner != null ? evowner.getName() : "<not find>";
    }
}
