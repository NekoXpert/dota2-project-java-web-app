package com.dota2.main.repository;

// Author: Felipe Reyes { Nekosor }
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dota2.main.model.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}
