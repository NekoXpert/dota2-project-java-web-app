package com.dota2.main.model;

import com.dota2.main.model.JugadorATTR.Medalla;
import com.dota2.main.model.JugadorATTR.Pais;

// Author: Felipe Reyes { Nekosor }
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
;

@Entity
@Table(name = "jugadores")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_Name")
    private String firstName;
    @Column(name = "dni_Dotero")
    private Integer dniDotero;
    @Enumerated(EnumType.STRING)
    private Medalla medallaRank;
    private Integer mmr;
    private Integer edad;
    @Enumerated(EnumType.STRING)
    private Pais nacionalidad;
    private String telefono;
    private String correo;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "torneo_id")
    private Torneo torneo;

    @ManyToOne
    @JoinColumn(name = "heroe_id")
    private Heroe heroe;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Medalla getMedallaRank() {
        return medallaRank;
    }

    public void setMedallaRank(Medalla medallaRank) {
        this.medallaRank = medallaRank;
    }

    public Pais getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Pais nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public Integer getMmr() {
        return mmr;
    }

    public void setMmr(Integer mmr) {
        this.mmr = mmr;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }



    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getDniDotero() {
        return dniDotero;
    }

    public void setDniDotero(Integer dniDotero) {
        this.dniDotero = dniDotero;
    }

  

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Heroe getHeroe() {
        return heroe;
    }

    public void setHeroe(Heroe heroe) {
        this.heroe = heroe;
    }

  

  @Override
public String toString() {
    return "Jugador{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", dniDotero='" + dniDotero + '\'' +
            ", medallaRank='" + medallaRank + '\'' +
            ", mmr=" + mmr +
            ", edad=" + edad +
            ", nacionalidad='" + nacionalidad + '\'' +
            ", telefono='" + telefono + '\'' +
            ", correo='" + correo + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", equipo=" + (equipo != null ? equipo.getId() : null) +
            ", torneo=" + (torneo != null ? torneo.getId() : null) +
            ", heroe=" + (heroe != null ? heroe.getId() : null) +
            '}';
}

}