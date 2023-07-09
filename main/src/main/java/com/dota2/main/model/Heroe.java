package com.dota2.main.model;

// Author: Felipe Reyes { Nekosor }
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;


import jakarta.persistence.JoinColumn;

import java.util.HashSet;
import java.util.Set;

import com.dota2.main.model.HeroeATTR.Atributo;
import com.dota2.main.model.HeroeATTR.Rol;

@Entity
public class Heroe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nameHero;
    @Enumerated(EnumType.STRING)
    private Rol rolHeroe;
    @Enumerated(EnumType.STRING)
    private Atributo atributoHeroe;
    private String imageUrl;
    @ManyToMany(mappedBy = "heroes")
    private Set<Torneo> torneos = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameHero() {
        return nameHero;
    }

    public Rol getRolHeroe() {
        return rolHeroe;
    }

    public void setRolHeroe(Rol rolHeroe) {
        this.rolHeroe = rolHeroe;
    }

    public Atributo getAtributoHeroe() {
        return atributoHeroe;
    }

    public void setAtributoHeroe(Atributo atributoHeroe) {
        this.atributoHeroe = atributoHeroe;
    }

    public void setNameHero(String nameHero) {
        this.nameHero = nameHero;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

   
    public Set<Torneo> getTorneos() {
        return torneos;
    }

    public void setTorneos(Set<Torneo> torneos) {
        this.torneos = torneos;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
public String toString() {
    return "Heroe{" +
            "id=" + id +
            ", nameHero='" + nameHero + '\'' +
            ", rolHeroe='" + rolHeroe + '\'' +
            ", atributoHeroe='" + atributoHeroe + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", torneos=" + (torneos != null ? torneos.size() : 0) +
            ", jugador=" + (jugador != null ? jugador.getId() : null) +
            '}';
}

  
}