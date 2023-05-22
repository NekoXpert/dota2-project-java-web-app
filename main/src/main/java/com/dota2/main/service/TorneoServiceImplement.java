package com.dota2.main.service;

// Author: Felipe Reyes { Nekosor }
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dota2.main.model.Torneo;
import com.dota2.main.repository.TorneoRepository;

@Service
public class TorneoServiceImplement implements TorneoService {
    @Autowired
    private TorneoRepository torneoRepository;

    @Override
    public List<Torneo> getAllTorneos() {
        return torneoRepository.findAll();
    }

    @Override
    public void saveTorneo(Torneo torneo) {
        this.torneoRepository.save(torneo);
    }

    @Override
    public Torneo getTorneoById(long id) {
        Optional<Torneo> optional = torneoRepository.findById(id);
        Torneo torneo = null;
        if (optional.isPresent()) {
            torneo = optional.get();
        } else {
            throw new RuntimeException(" Torneo not found for id :: " + id);
        }
        return torneo;
    }

    @Override
    public void deleteTorneoById(long id) {
        this.torneoRepository.deleteById(id);
    }
}