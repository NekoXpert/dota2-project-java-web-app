package com.dota2.main.model;
// Author: Felipe Reyes { Nekosor }

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


import java.util.HashSet;
import java.util.Set;



import jakarta.persistence.OneToMany;


@Entity
public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String name;

    private Integer premio;
    private String imageUrl;
    @ManyToMany
    private Set<Heroe> heroes = new HashSet<>();
    @OneToMany(mappedBy = "torneo")
    private Set<Jugador> jugadores = new HashSet<>();

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

    public Integer getPremio() {
        return premio;
    }

    public void setPremio(Integer premio) {
        this.premio = premio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Heroe> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<Heroe> heroes) {
        this.heroes = heroes;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "Torneo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", premio='" + premio + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", heroes=" + (heroes != null ? heroes.size() : 0) +
                ", jugadores=" + (jugadores != null ? jugadores.size() : 0) +
                '}';
    }

    // getters y setters

}
