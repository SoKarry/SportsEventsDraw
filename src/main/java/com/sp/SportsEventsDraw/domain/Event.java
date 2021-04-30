package com.sp.SportsEventsDraw.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

//Сущность спортивного события
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Заполните поле название")
    @Length(max=255, message = "Слишком большое название (введите до 255 символов)")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @Transient
    @NotBlank(message = "Введите игроков")
    private String pl_names;
    @OneToMany(mappedBy = "evowner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Game> games;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "draw_id")
    private Draw draw;

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

    public String getPl_names() {
        return pl_names;
    }

    public void setPl_names(String pl_names) {
        this.pl_names = pl_names;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Draw getDraw() {
        return draw;
    }

    public void setDraw(Draw draw) {
        this.draw = draw;
    }
}
