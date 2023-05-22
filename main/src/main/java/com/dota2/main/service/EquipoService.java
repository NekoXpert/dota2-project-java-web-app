package com.dota2.main.service;

// Author: Felipe Reyes { Nekosor }
import java.util.List;

import com.dota2.main.model.Equipo;

public interface EquipoService {
    List<Equipo> getAllEquipos();

    void saveEquipo(Equipo equipo);

    Equipo getEquipoById(long id);

    void deleteEquipoById(long id);
}