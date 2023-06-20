package com.dota2.main.model;
// Author: Felipe Reyes { Nekosor }

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.dota2.main.model.EquipoATTR.DPC;
import com.dota2.main.model.EquipoATTR.Zona;

@Entity
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private Zona region;
    @Enumerated(EnumType.STRING)
    private DPC puntosClasificacion;
    private String imageUrl;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private List<Jugador> jugadores;

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public DPC getPuntosClasificacion() {
        return puntosClasificacion;
    }

    public void setPuntosClasificacion(DPC puntosClasificacion) {
        this.puntosClasificacion = puntosClasificacion;
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

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Zona getRegion() {
        return region;
    }

    public void setRegion(Zona region) {
        this.region = region;
    }

 
@Override
public String toString() {
    return "Equipo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", region='" + region + '\'' +
            ", puntosClasificacion='" + puntosClasificacion + '\'' +
            ", imageUrl=" + imageUrl + '\'' +
            ", jugadores=" + jugadores + '\'' +
            
            '}';
}
    // getters y setters

}
