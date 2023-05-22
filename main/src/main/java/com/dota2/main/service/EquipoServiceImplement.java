package com.dota2.main.service;

// Author: Felipe Reyes { Nekosor }
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dota2.main.model.Equipo;
import com.dota2.main.repository.EquipoRepository;

@Service
public class EquipoServiceImplement implements EquipoService {
    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    @Override
    public void saveEquipo(Equipo equipo) {
        this.equipoRepository.save(equipo);
    }

    @Override
    public Equipo getEquipoById(long id) {
        Optional<Equipo> optional = equipoRepository.findById(id);
        Equipo equipo = null;
        if (optional.isPresent()) {
            equipo = optional.get();
        } else {
            throw new RuntimeException(" Equipo not found for id :: " + id);
        }
        return equipo;
    }

    @Override
    public void deleteEquipoById(long id) {
        this.equipoRepository.deleteById(id);
    }
}