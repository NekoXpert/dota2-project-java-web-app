package com.dota2.main.service;

// Author: Felipe Reyes { Nekosor }
import java.util.List;

import com.dota2.main.model.Torneo;

public interface TorneoService {
    List<Torneo> getAllTorneos();

    void saveTorneo(Torneo torneo);

    Torneo getTorneoById(long id);

    void deleteTorneoById(long id);
}