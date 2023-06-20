package com.dota2.main.service;

// Author: Felipe Reyes { Nekosor }
import com.dota2.main.model.Heroe;
import com.dota2.main.repository.HeroeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class HeroeServiceImplement implements HeroeService {

    @Autowired
    private HeroeRepository heroeRepository;

    @Override
    public List<Heroe> getAllHeroes() {
        return heroeRepository.findAll();
    }


     @Override
    public  Heroe saveHeroe(Heroe heroe) {
        return heroeRepository.save(heroe);
    }


    @Override
    public Heroe getHeroeById(long id) {
        Optional<Heroe> optional = heroeRepository.findById(id);
        return optional.orElseThrow(() -> new RuntimeException("Heroe no encontrado por id: " + id));
    }

    @Override
    public void deleteHeroeById(long id) {
        heroeRepository.deleteById(id);
    }


}