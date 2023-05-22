package com.dota2.main.model;

// Author: Felipe Reyes { Nekosor }
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Heroe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameHero;
    @ManyToMany(mappedBy = "heroes")
    private Set<Torneo> torneos = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;


    
    @Lob
    private byte[] image;

    @Transient
    private MultipartFile imageFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameHero() {
        return nameHero;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
        try {
            this.image = imageFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Resto de la implementación de la clase Heroe

    @Override
    public String toString() {
        return "Heroe [id=" + id + ", nameHero=" + nameHero + ", jugador=" + jugador + "]";
    }
}