package com.dota2.main.service;

// Author: Felipe Reyes { Nekosor }
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dota2.main.model.Jugador;
import com.dota2.main.repository.JugadorRepository;

@Service
public class JugadorServiceImplement implements JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Override
    public List<Jugador> getAllJugadores() {

        return jugadorRepository.findAll();
    }

    @Override
    public void saveJugador(Jugador jugador) {
        this.jugadorRepository.save(jugador);

    }

    @Override
    public Jugador getJugadorById(long id) {

        Optional<Jugador> optional = jugadorRepository.findById(id);
        Jugador jugador = null;

        if (optional.isPresent()) {
            jugador = optional.get();
        } else {
            throw new RuntimeException(" Jugador not found for id :: " + id);
        }
        return jugador;
    }

    @Override
    public void deleteJugadorById(long id) {

        this.jugadorRepository.deleteById(id);
    }

}
