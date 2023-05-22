package com.dota2.main.service;

// Author: Felipe Reyes { Nekosor }
import java.util.List;

import com.dota2.main.model.Jugador;

public interface JugadorService {

    List<Jugador> getAllJugadores();

    void saveJugador(Jugador jugador);

    Jugador getJugadorById(long id);

    void deleteJugadorById(long id);

}
