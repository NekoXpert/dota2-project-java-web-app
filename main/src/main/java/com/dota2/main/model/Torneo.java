package com.dota2.main.model;
// Author: Felipe Reyes { Nekosor }

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.OneToMany;


@Entity
public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

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


    public Set<Jugador> getJugadores() {
        return jugadores;
    }


    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    // getters y setters

    
}
