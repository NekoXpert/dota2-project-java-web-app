package com.dota2.main.service;

// Author: Felipe Reyes { Nekosor }
import com.dota2.main.model.Heroe;


import java.util.List;



public interface HeroeService {

    List<Heroe> getAllHeroes();

    Heroe saveHeroe(Heroe heroe);

    Heroe getHeroeById(long id);

    void deleteHeroeById(long id);
}