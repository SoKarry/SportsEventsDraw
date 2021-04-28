package com.sp.SportsEventsDraw.domain;

import javax.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String plname;

    public Player(){
    }

    public Player(String plname) {
        this.plname = plname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlname() {
        return plname;
    }

    public void setPlname(String plname) {
        this.plname = plname;
    }


}
